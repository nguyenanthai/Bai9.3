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

        assertTrue(worker.isRunning()...), "Trạng thái running phải là false sau khi gọi stop()");
        assertFalse(thread.isAlive(), "Thread phải thực sự kết thúc");
    }
}