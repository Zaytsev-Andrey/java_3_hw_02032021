package hw4;

public class Main {
    private static final int COUNTER = 5;
    private static final Object LOCK = new Object();
    private static volatile char ch = 'A';

    public static void main(String[] args) {
        printA();
        printB();
        printC();
    }

    private static void printA() {
        new Thread((() -> {
            try {
                for (int i = 0; i < COUNTER; i++) {
                    synchronized (LOCK) {
                        while (ch != 'A') {
                            LOCK.wait();
                        }

                        System.out.print(ch);
                        ch = 'B';
                        LOCK.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }

    private static void printB() {
        new Thread((() -> {
            try {
                for (int i = 0; i < COUNTER; i++) {
                    synchronized (LOCK) {
                        while (ch != 'B') {
                            LOCK.wait();
                        }

                        System.out.print(ch);
                        ch = 'C';
                        LOCK.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }

    private static void printC() {
        new Thread((() -> {
            try {
                for (int i = 0; i < COUNTER; i++) {
                    synchronized (LOCK) {
                        while (ch != 'C') {
                            LOCK.wait();
                        }

                        System.out.print(ch);
                        ch = 'A';
                        LOCK.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }
}
