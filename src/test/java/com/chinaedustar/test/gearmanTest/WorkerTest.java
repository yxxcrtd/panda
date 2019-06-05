package com.chinaedustar.test.gearmanTest;

import com.chinaedustar.common.gearman.worker.BaseWorkerRunner;

public class WorkerTest {

    public static void main(String[] args) {
        BaseWorkerRunner.start("test");
    }
}
