package com.uet.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    @Test
    void testWorkerStop() throws InterruptedException {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);

        thread.start();
        assertTrue(worker.isRunning(), "Worker phải đang chạy sau khi khởi động");

        // Đợi một chút rồi gọi stop
        Thread.sleep(200);
        worker.stop();
        thread.join(1000);

        assertFalse(worker.isRunning(), "Trạng thái running phải là false sau khi gọi stop()");
        assertFalse(thread.isAlive(), "Thread phải thực sự kết thúc");
    }

    // BỔ SUNG 1: Bắt trường hợp Worker bị ngắt (cover khối catch InterruptedException)
    @Test
    void testWorkerInterruption() throws InterruptedException {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();

        // Đợi luồng chạy và rơi vào trạng thái Thread.sleep(100)
        Thread.sleep(50);

        // Ép văng lỗi InterruptedException
        thread.interrupt();

        // Đợi một chút để khối catch ghi log, sau đó dừng an toàn
        Thread.sleep(50);
        worker.stop();
        thread.join(1000);

        assertFalse(worker.isRunning());
    }

    // BỔ SUNG 2: Chạy thử hàm Main để tăng coverage cho Main.java
    @Test
    void testMainExecution() {
        assertDoesNotThrow(() -> {
            // Chạy trực tiếp hàm main
            Main.main(new String[]{});
        }, "Hàm main phải chạy thành công mà không ném ra ngoại lệ");
    }

    // TEST CỦA BÀI 4: Matrix Strategy (Đã refactor)
    @Test
    void testFilePath_Refactored_PassOnAllOS() {
        String dir = "logs";
        String fileName = "app.log";

        String expectedPath = dir + java.io.File.separator + fileName;
        java.nio.file.Path actualPath = java.nio.file.Paths.get(dir, fileName);

        assertEquals(expectedPath, actualPath.toString(), "Đường dẫn phải khớp trên mọi OS");
    }
}