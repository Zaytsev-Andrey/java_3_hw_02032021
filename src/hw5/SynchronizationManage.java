package hw5;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationManage {
    private CountDownLatch waitingReady;
    private CyclicBarrier waitingStart;
    private Semaphore tunnelSemaphore;
    private BlockingQueue<Car> finishers;
    private Lock finishLock;

    public SynchronizationManage(int carsCount) {
        this.waitingReady = new CountDownLatch(carsCount);
        this.waitingStart = new CyclicBarrier(carsCount + 1);
        this.tunnelSemaphore = new Semaphore(carsCount / 2);
        this.finishers = new ArrayBlockingQueue<>(carsCount);
        this.finishLock = new ReentrantLock();
    }

    public CountDownLatch getWaitingReady() {
        return waitingReady;
    }

    public CyclicBarrier getWaitingStart() {
        return waitingStart;
    }

    public Semaphore getTunnelSemaphore() {
        return tunnelSemaphore;
    }

    public void putFinisher(Car car) throws InterruptedException {
        finishers.put(car);
    }

    public Car takeFinisher() throws InterruptedException {
        return finishers.take();
    }

    public Lock getFinishLock() {
        return finishLock;
    }
}
