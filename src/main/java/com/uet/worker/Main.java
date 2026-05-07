import java.util.*;
package com.uet.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //Bắt đầu chương trình, chạy test 1
        logger.info("Chương trình bắt đầu.");
        //Giữ nguyên như cũ

        Worker worker = new Worker();
        Thread thread = new Thread(worker, "Worker-Thread");
        thread.start();

        try {
            // Cho luồng chạy khoảng 1 giây
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Main thread bị gián đoạn", e);
        }

        worker.stop();

        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.error("Lỗi khi chờ worker kết thúc", e);
        }

        logger.info("Chương trình kết thúc.");
    }
}