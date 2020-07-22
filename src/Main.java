import SECTION_1.ConcurrentQueue;

public class Main {

    public static void main(String[] args) {
        ConcurrentQueue concurrentQueue = new ConcurrentQueue();
        concurrentQueue.Enq(1);
        concurrentQueue.Enq(2);
        concurrentQueue.Enq(3);
        concurrentQueue.Enq(4);

//        concurrentQueue.print();
        Integer val = concurrentQueue.Deq();
        val = concurrentQueue.Deq();
        val = concurrentQueue.Deq();
        val = concurrentQueue.Deq();
        val = concurrentQueue.Deq();
//        System.out.println(val);
//        concurrentQueue.print();
    }
}
