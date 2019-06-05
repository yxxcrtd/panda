package com.chinaedustar.test;

import com.chinaedustar.common.cache.CacheFactory;
import com.chinaedustar.common.cache.CacheService;

public class CacheTest {

    public static void main(String[] args) {

        CacheService cacheService = CacheFactory.getInstance("test1");
        cacheService.add("test", "test1");
        System.out.println(cacheService.get("test"));
    }
}
