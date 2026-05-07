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
    void testFilePath_Hardcoded_FailOnWindows() {
        String dir = "logs";
        String fileName = "app.log";

        // Cố tình nối chuỗi theo kiểu Linux/Mac (Hardcoded)
        String expectedPath = dir + "/" + fileName;

        // Sinh đường dẫn thực tế theo hệ điều hành đang chạy bằng API của Java
        java.nio.file.Path actualPath = java.nio.file.Paths.get(dir, fileName);

        // So sánh: Sẽ PASS trên Ubuntu/Mac nhưng FAIL trên Windows (vì Windows dùng \ )
        assertEquals(expectedPath, actualPath.toString(), "Định dạng đường dẫn không khớp!");
    }
}