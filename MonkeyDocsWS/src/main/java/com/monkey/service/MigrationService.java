package com.monkey.service;

import com.monkey.dao.DeltaDAO;
import com.monkey.entity.Delta;
import com.monkey.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class MigrationService {
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private DeltaDAO deltaDAO;
    @Autowired
    private HistoryService historyService;
    public MigrationThread createThread(int docId) {
        return new MigrationThread(docId);
    }
    public class MigrationThread extends Thread {
        private final int docId;
        private int userId = 0;
        private boolean running = true;
        private final Queue<Delta> buffer = new ConcurrentLinkedQueue<>();
        public MigrationThread(int docId) {
            this.docId = docId;
        }
        public void consume(Delta data) {
            buffer.add(data);
        }
        public void readyToClose() {
            running = false;
        }
        public void readyToClose(int userId) {
            running = false;
            this.userId = userId;
        }
        @Override
        public void run() {
            while (running || buffer.size() > 0) {
                if (buffer.size() == 0) Thread.yield();
                else {
                    Delta delta= buffer.poll();
                    deltaDAO.create(delta);
                }
            }
            // Unload data from redis when the migration thread finished
            clientManager.unregisterMigration(docId);
            historyService.unload(docId);
        }
    }
}
