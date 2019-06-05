-- 更改字典表的字段长度
-- alter table Dictionary modify column DictType varchar(11) not null;
-- alter table Archivement modify column Category varchar(11) not null;
-- alter table Archivement add column UserId varchar(40) not null;
-- alter table ArchivementExtra drop column Status;
-- alter table Archivement add column HotCount int not null default 0;

-- 数据字典的初始化

-- 论文类型
replace into `Archivement`.`Dictionary` (`DictType`, `Name`, `ItemIndex`) values ('thesis', '期刊论文', 1), ('thesis', '会议论文', 2), ('thesis', '研究报告', 3), 
('thesis', '研究论文', 4), ('thesis', '其他论文', 5);

-- 著作类型
replace into `Archivement`.`Dictionary` (`DictType`, `Name`, `ItemIndex`) values 
('book', '专著', 1), ('book', '编著', 2), ('book', '译著', 3);

-- 教学设计类型
replace into `Archivement`.`Dictionary` (`DictType`, `Name`, `ItemIndex`) values 
('instruction', '教案', 1), ('instruction', '学案', 2);

-- 专利类型
replace into `Archivement`.`Dictionary` (`DictType`, `Name`, `ItemIndex`) values 
('patent', '发明专利', 1), ('patent', '外观设计', 2), ('patent', '实用新型', 3);

-- 其他类型
replace into `Archivement`.`Dictionary` (`DictType`, `Name`, `ItemIndex`) values 
('other', '工具书', 1), ('other', '教材', 2), ('other', '电脑软件', 3), ('other', '其他', 4);


-- 获奖等级
replace into `Archivement`.`Dictionary` (`DictType`, `Name`, `ItemIndex`) values 
('prizeLevel', '国家', 1),('prizeLevel', '省级', 2),('prizeLevel', '市级', 3),('prizeLevel', '校级', 4);

-- 统计表
replace into `Archivement`.`Stat` (`UserId`) values ('Site');


CREATE PROCEDURE uuid7()
BEGIN
	DECLARE i int;
	DECLARE name varchar(100);
	
	SET i = 1;
	SET name = "中文";
	
	loop1: WHILE i <= 10 DO
		insert into test values (i, concat(UUID(), name, i));
		SET i = i + 1;
	END WHILE loop1;
END;


/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-3-7 16:18:16                            */
/*==============================================================*/

drop table if exists Archivement;

drop table if exists ArchivementExtra;

drop table if exists Author;

drop table if exists Dictionary;

drop table if exists Keyword;

drop table if exists Operation;

drop table if exists Stat;

/*==============================================================*/
/* Table: Archivement 科研成果基础数据表                                      */
/*==============================================================*/
create table Archivement
(
   ArchId				int(4) not null auto_increment comment '主键，自增长',
   UserId               varchar(40) not null,
   Category				varchar(11) not null comment '论文：thesis、著作：book、课件：courseware、教学设计：instruction、专利：patent、其他：other',
   Type                 smallint comment '类型。课件没有类型',
   Title				varchar(50) not null comment '科研成果名称',
   Publish				bool not null default FALSE comment '默认是：false或者0 未出版，如果是已出版，则刊物名称和出版时间都是必添。另外：著作默认是已出版',
   Prize				bool not null comment '是否获奖，默认false或者0：未获奖',
   Author				varchar(128) not null comment '科研成果作者，默认是发布人自己',
   Keyword				varchar(128) comment '关键字',
   ViewCount			smallint not null default 0 comment '浏览数',
   CollectCount			smallint not null default 0 comment '收藏数',
   PraiseCount			smallint not null default 0 comment '点赞数',
   HotCount				int not null default 0 comment '热度值 = 浏览数 + 收藏数 + 点赞数',
   GradeId				smallint comment '学段Id或者年级Id：基教有，非基教没有',
   SubjectId			smallint comment '学科Id：',
   CreateTime			bigint not null,
   TopicResearchId		int comment '有ID就是来自课题研究，没有就是非课题研究科研成果',
   TopicResearchStageId	int comment '课题研究阶段ID，来自课题研究应用设置',
   primary key (ArchId)
);

/*==============================================================*/
/* Table: ArchivementExtra        科研成果扩展属性表                              */
/*==============================================================*/
create table ArchivementExtra
(
   ArchId               int(4) not null comment '外键，程序插入',
   Summary              varchar(3000) comment '科研成果描述',
   PublishName          varchar(40) comment '刊物名称',
   PublishTime          date comment '出版时间',
   PrizeItem            varchar(40) comment '奖项名称',
   PrizeLevel			smallint comment '奖项等级',
   PrizeTime			date comment '获奖时间',
   ServicePatent		bool comment '专利特有 是否是职务专利：默认false，未职务',
   Authorize            bool comment '专利特有 是否授权，默认非授权 false',
   ApplyNumber          varchar(20) comment '专利特有 申请号',
   ApplyTime            date comment '专利特有 申请时间',
   Number               varchar(20) comment '专利特有 专利号',
   ObtainTime           date comment '专利特有 专利获得时间',
   Agency               varchar(128) comment '专利特有 委托代理机构',
   Agent                varchar(10) comment '专利特有 代理人',
   primary key (ArchId)
);

/*==============================================================*/
/* Table: Author        科研成果作者表                                        */
/*==============================================================*/
create table Author
(
   Id                   int(4) not null auto_increment comment '主键，自增长',
   ArchId               int(4) not null comment '科研成果Id',
   Name                 varchar(20) not null comment '人名或机构名称',
   NameId               varchar(40) comment '系统中的用户或机构有对应的ID，手添为空',
   primary key (Id)
);

/*==============================================================*/
/* Table: Dictionary      数据字典                                      */
/*==============================================================*/
create table Dictionary
(
   Id					int(4) not null auto_increment comment '主键，自增长',
   DictType				varchar(11) not null comment '字典存储内容类别，英文简写',
   Name					varchar(10) not null comment '字典存储的内容名称，MySQL可以接受10个汉字',
   ItemIndex			smallint comment '默认从小到大排序（MySQL的短整型最大长度是：+32767）',
   primary key (Id)
);

/*==============================================================*/
/* Table: Keyword       关键字                                        */
/*==============================================================*/
create table Keyword
(
   Id                   int(4) not null auto_increment comment '主键，自增长',
   ArchId               int(4) not null comment '科研成果Id',
   Name                 varchar(20) not null comment '关键词名称',
   Count				int(4) not null comment '关键词出现的次数',
   primary key (Id)
);

/*==============================================================*/
/* Table: Operation          用户互动表                                   */
/*==============================================================*/
create table Operation
(
   Id                   int(4) not null auto_increment comment '主键，自增长',
   ArchId               int(4) not null comment '科研成果Id',
   Praise               bool not null default FALSE comment '是否点赞',
   Collect              bool not null default FALSE comment '是否收藏',
   UserId               varchar(40) not null,
   primary key (Id)
);

alter table Operation comment '用户有操作的时候记录';

/*==============================================================*/
/* Table: Stat        科研成果统计表                                          */
/*==============================================================*/
create table Stat
(
   Id					int(4) not null auto_increment comment '主键，自增长',
   UserId				varchar(40) not null comment '用户统计，整站统计实时统计',
   Thesis				int not null comment '论文数量',
   Book					int not null comment '著作数量',
   Courseware			int not null comment '课件数量',
   Instruction			int not null comment '教学设计数量',
   Patent				int not null comment '专利数量',
   Other				int not null comment '其他成果数量',
   TotalCount			int not null comment '以上6项之和',
   primary key (Id)
);

alter table ArchivementExtra add constraint FK_Archivement_Has_Extra foreign key (ArchId)
      references Archivement (ArchId) on delete restrict on update restrict;

alter table Author add constraint FK_Archivement_Has_Author foreign key (ArchId)
      references Archivement (ArchId) on delete restrict on update restrict;

alter table Keyword add constraint FK_Research_Has_Keyword foreign key (ArchId)
      references Archivement (ArchId) on delete restrict on update restrict;

alter table Operation add constraint FK_User_Praise_Or_Collect foreign key (ArchId)
      references Archivement (ArchId) on delete restrict on update restrict;


