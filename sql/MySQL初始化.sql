CREATE DATABASE `Archivement` DEFAULT CHARACTER SET utf8;

USE Archivement;

CREATE TABLE `Archivement` (
  `ArchId` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `UserId` varchar(40) NOT NULL,
  `Category` varchar(11) NOT NULL COMMENT '论文：thesis、著作：book、课件：courseware、教学设计：instruction、专利：patent、其他：other',
  `Type` smallint(6) DEFAULT NULL COMMENT '类型。课件没有类型',
  `Title` varchar(50) NOT NULL COMMENT '科研成果名称',
  `Publish` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认是：false或者0 未出版，如果是已出版，则刊物名称和出版时间都是必添。另外：著作默认是已出版',
  `Prize` tinyint(1) NOT NULL COMMENT '是否获奖，默认false或者0：未获奖',
  `Author` varchar(128) NOT NULL COMMENT '科研成果作者，默认是发布人自己',
  `Keyword` varchar(128) DEFAULT NULL COMMENT '关键字',
  `ViewCount` smallint(6) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `CollectCount` smallint(6) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `PraiseCount` smallint(6) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `EduType` smallint(6) DEFAULT NULL COMMENT '教育类型Id：分为：基础教育、职业教育、幼儿教育等',
  `GradeId` smallint(6) DEFAULT NULL COMMENT '学段Id或者年级Id：基教有，非基教没有',
  `SubjectId` smallint(6) DEFAULT NULL COMMENT '学科Id：',
  `CreateTime` bigint(20) NOT NULL,
  `TopicResearchId` int(11) DEFAULT NULL COMMENT '有ID就是来自课题研究，没有就是非课题研究科研成果',
  `TopicResearchStageId` int(11) DEFAULT NULL COMMENT '课题研究阶段ID，来自课题研究应用设置',
  `TopicResearchName` varchar(32) DEFAULT NULL,
  `HotCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ArchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `ArchivementExtra` (
  `ArchId` int(4) NOT NULL COMMENT '外键，程序插入',
  `Summary` varchar(3000) DEFAULT NULL COMMENT '科研成果描述',
  `PublishName` varchar(40) DEFAULT NULL COMMENT '刊物名称',
  `PublishTime` date DEFAULT NULL COMMENT '出版时间',
  `PrizeItem` varchar(10) DEFAULT NULL COMMENT '奖项名称',
  `PrizeLevel` smallint(6) DEFAULT NULL COMMENT '奖项等级',
  `PrizeTime` date DEFAULT NULL COMMENT '获奖时间',
  `ServicePatent` tinyint(1) DEFAULT NULL COMMENT '专利特有 是否是职务专利：默认false，未职务',
  `Authorize` tinyint(1) DEFAULT NULL COMMENT '专利特有 是否授权，默认非授权 false',
  `Status` smallint(6) DEFAULT NULL COMMENT '专利特有 专利状态',
  `ApplyNumber` varchar(20) DEFAULT NULL COMMENT '专利特有 申请号',
  `ApplyTime` date DEFAULT NULL COMMENT '专利特有 申请时间',
  `Number` varchar(20) DEFAULT NULL COMMENT '专利特有 专利号',
  `ObtainTime` date DEFAULT NULL COMMENT '专利特有 专利获得时间',
  `Agency` varchar(128) DEFAULT NULL COMMENT '专利特有 委托代理机构',
  `Agent` varchar(10) DEFAULT NULL COMMENT '专利特有 代理人',
  PRIMARY KEY (`ArchId`),
  CONSTRAINT `FK_Archivement_Has_Extra` FOREIGN KEY (`ArchId`) REFERENCES `Archivement` (`ArchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Author` (
  `Id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `ArchId` int(4) NOT NULL COMMENT '科研成果Id',
  `Name` varchar(20) NOT NULL COMMENT '人名或机构名称',
  `NameId` varchar(40) DEFAULT NULL COMMENT '系统中的用户或机构有对应的ID，手添为空',
  `UnitId` int(11) DEFAULT NULL,
  `UnitPath` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_Archivement_Has_Author` (`ArchId`),
  CONSTRAINT `FK_Archivement_Has_Author` FOREIGN KEY (`ArchId`) REFERENCES `Archivement` (`ArchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Dictionary` (
  `Id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `DictType` varchar(11) NOT NULL COMMENT '字典存储内容类别，英文简写',
  `Name` varchar(10) NOT NULL COMMENT '字典存储的内容名称，MySQL可以接受10个汉字',
  `ItemIndex` smallint(6) DEFAULT NULL COMMENT '默认从小到大排序（MySQL的短整型最大长度是：+32767）',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Keyword` (
  `Id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `ArchId` int(4) NOT NULL COMMENT '科研成果Id',
  `Name` varchar(20) NOT NULL COMMENT '关键词名称',
  `Count` int(4) NOT NULL COMMENT '关键词出现的次数',
  PRIMARY KEY (`Id`),
  KEY `FK_Research_Has_Keyword` (`ArchId`),
  CONSTRAINT `FK_Research_Has_Keyword` FOREIGN KEY (`ArchId`) REFERENCES `Archivement` (`ArchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Operation` (
  `Id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `ArchId` int(4) NOT NULL COMMENT '科研成果Id',
  `Praise` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否点赞',
  `Collect` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否收藏',
  `UserId` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_User_Praise_Or_Collect` (`ArchId`),
  CONSTRAINT `FK_User_Praise_Or_StatCollect` FOREIGN KEY (`ArchId`) REFERENCES `Archivement` (`ArchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户有操作的时候记录';

CREATE TABLE `Stat` (
  `Id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `UserId` varchar(40) NOT NULL COMMENT '用户统计，整站统计实时统计',
  `Thesis` int(11) NOT NULL COMMENT '论文数量',
  `Book` int(11) NOT NULL COMMENT '著作数量',
  `Courseware` int(11) NOT NULL COMMENT '课件数量',
  `Instruction` int(11) NOT NULL COMMENT '教学设计数量',
  `Patent` int(11) NOT NULL COMMENT '专利数量',
  `Other` int(11) NOT NULL COMMENT '其他成果数量',
  `TotalCount` int(11) NOT NULL COMMENT '以上6项之和',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `Dictionary` WRITE;
/*!40000 ALTER TABLE `Dictionary` DISABLE KEYS */;
INSERT INTO `Dictionary` VALUES (1,'thesis','期刊论文',1),(2,'thesis','会议论文',2),(3,'thesis','研究报告',3),(4,'thesis','研究论文',4),(5,'thesis','其他论文',5),(6,'book','专著',1),(7,'book','编著',2),(8,'book','译著',3),(9,'instruction','教案',1),(10,'instruction','学案',2),(11,'patent','发明专利',1),(12,'patent','外观设计',2),(13,'patent','实用新型',3),(14,'other','工具书',1),(15,'other','教材',2),(16,'other','电脑软件',3),(17,'other','其他',4),(18,'prizeLevel','国家',1),(19,'prizeLevel','省级',2),(20,'prizeLevel','市级',3),(21,'prizeLevel','校级',4);
/*!40000 ALTER TABLE `Dictionary` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `Stat` WRITE;
INSERT INTO `Stat` (`UserId`, `Thesis`, `Book`, `Courseware`, `Instruction`, `Patent`, `Other`, `TotalCount`) VALUES ('Site', '0', '0', '0', '0', '0', '0', '0');
UNLOCK TABLES;