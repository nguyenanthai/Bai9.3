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

        // Đợi 200ms rồi dừng
        Thread.sleep(200);
        worker.stop();
        thread.join(1000); // Đợi tối đa 1 giây để thread kết thúc

        assertFalse(worker.isRunning(), "Trạng thái running phải là false sau khi gọi stop()");
        assertFalse(thread.isAlive(), "Thread phải thực sự kết thúc");
    }
    @Test
    void testFilePath_Refactored_PassOnAllOS() {
        String dir = "logs";
        String fileName = "app.log";

        // REFACTOR: Sử dụng File.separator thay vì fix cứng "/" hoặc "\"
        String expectedPath = dir + java.io.File.separator + fileName;

        // Có thể dùng luôn Path API để tạo expectedPath cho an toàn tuyệt đối:
        // String expectedPath = java.nio.file.Paths.get(dir, fileName).toString();

        java.nio.file.Path actualPath = java.nio.file.Paths.get(dir, fileName);

        assertEquals(expectedPath, actualPath.toString(), "Đường dẫn phải khớp trên mọi OS");
    }
}