package SECTION_2;


import INTERFACES.Queue;
import SECTION_1.ConcurrentQueue;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentQueueMichaelScottAlg extends ConcurrentQueue {
    @Override
    public void Enq(Integer item) {
                Node  newNode = new Node(item);
        boolean success;
        do{
            Node curTail = tail.get();
            success = curTail.next.compareAndSet(null, newNode);
            tail.compareAndSet(curTail, curTail.next.get());
        }while (!success);
    }
}