import SECTION_1.ConcurrentQueue;
import SECTION_2.WFQueue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) throws Exception {
            ConcurrentQueue concurrentQueue = new ConcurrentQueue();
//        concurrentQueue.Enq(1);
//        concurrentQueue.Enq(2);
//        concurrentQueue.Enq(3);
//        concurrentQueue.Enq(4);
//
//        int numOfElements = concurrentQueue.Count();
//
//        System.out.println("Thre are " + numOfElements);
//
//        Integer val = concurrentQueue.Deq();
//        numOfElements = concurrentQueue.Count();



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


        int numOfElements = concurrentQueue.Count();
        numOfElements = concurrentQueue.Count();

    }
}
