import Unbound_Queue.ConcurrentQueue;

public class Main {

    public static void main(String[] args) {
        ConcurrentQueue concurrentQueue = new ConcurrentQueue();
        concurrentQueue.Enq(1);
        concurrentQueue.Enq(2);
        concurrentQueue.Enq(3);
        concurrentQueue.Enq(4);

//        concurrentQueue.print();
        int val = concurrentQueue.Deq();
        concurrentQueue.Deq();
        concurrentQueue.Deq();
        concurrentQueue.Deq();
        concurrentQueue.Deq();
//        System.out.println(val);
//        concurrentQueue.print();
    }
}
