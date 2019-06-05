package com.chinaedustar.test.gearmanTest;

import com.chinaedustar.common.gearman.client.GearmanClientUtils;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                String value = GearmanClientUtils.executeSyn("test", "liangzh");
                System.out.println(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
