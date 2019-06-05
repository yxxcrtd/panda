package com.chinaedustar.test.gearmanTest;

import com.chinaedustar.common.gearman.worker.BaseGearmanFunction;
import com.chinaedustar.common.gearman.worker.GearmanFunctionAnn;

@GearmanFunctionAnn("test")
public class TestFunction extends BaseGearmanFunction {
    @Override
    public String doInFunction(String param) {
        System.out.println(param);
        return new StringBuffer(param).reverse().toString();
    }
}