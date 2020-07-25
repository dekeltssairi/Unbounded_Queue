import SECTION_1.ConcurrentQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentQueue concurrentQueue = new ConcurrentQueue();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++){
                    concurrentQueue.Enq(i);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++){
                    concurrentQueue.Enq(i);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int numOfElements = concurrentQueue.Count();
//
//        // waiting
//
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++){
                    concurrentQueue.Deq();
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++){
                    concurrentQueue.Deq();
                }
            }
        });



        t3.start();
        t4.start();

        t3.join();
        t4.join();

        numOfElements = concurrentQueue.Count();

    }
}
