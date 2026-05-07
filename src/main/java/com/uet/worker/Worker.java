package com.uet.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    // volatile đảm bảo các luồng nhìn thấy giá trị mới nhất ngay lập tức
    private volatile boolean running = true;

    public void stop() {
        logger.info("Nhận tín hiệu dừng (stop)...");
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        while (running) {
            logger.debug("Working...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Worker bị ngắt do InterruptedException.");
            }
        }
        logger.info("Worker đã dừng hoạt động an toàn.");
    }
}