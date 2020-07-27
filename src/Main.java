import SECTION_2.ConcurrentQueueMichaelScottAlg;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentQueueMichaelScottAlg concurrentQueue = new ConcurrentQueueMichaelScottAlg();

//        concurrentQueue.Enq(0);
//        concurrentQueue.Enq(1);
//
//        Integer val = concurrentQueue.Deq();
//        val = concurrentQueue.Deq();


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
                for (int i = 0; i < 10000; i++){
                    concurrentQueue.Deq();
                }
            }
        });

        t1.start();
        t3.start();
        t1.join();
        t3.join();

        int numOfElements = concurrentQueue.Count();
        int numOfElementsFew = concurrentQueue.Count();
        System.out.println(numOfElements);















        //       t3.start();
        //       t4.start();
//
        //       t3.join();
        //       t4.join();
//
        //       numOfElements = concurrentQueue.Count();
//        ConcurrentQueue concurrentQueue = new ConcurrentQueue();
//
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100000; i++){
//                    concurrentQueue.Enq(i);
//                }
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100000; i++){
//                    concurrentQueue.Enq(i);
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        int numOfElements = concurrentQueue.Count();
////
////        // waiting
////
//        Thread t3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10000; i++){
//                    concurrentQueue.Deq();
//                }
//            }
//        });
//
//        Thread t4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10000; i++){
//                    concurrentQueue.Deq();
//                }
//            }
//        });
//
//
//
//        t3.start();
//        t4.start();
//
//        t3.join();
//        t4.join();
//
//        numOfElements = concurrentQueue.Count();
//
//
    }
}
