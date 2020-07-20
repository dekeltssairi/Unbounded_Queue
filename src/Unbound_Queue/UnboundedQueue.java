package Unbound_Queue;

import java.util.concurrent.atomic.AtomicReference;

public class UnboundedQueue implements Queue {

    private AtomicReference<QueueNode> head;
    private AtomicReference<QueueNode> tail;
    public UnboundedQueue() {
        head = new AtomicReference();
    }

    @Override
    public void Enq(int val) {
        QueueNode newTail = new QueueNode(val);
        QueueNode tailNode = null;
        do
        {
            tailNode = tail.get();
            tailNode.next.set(newTail);
        }while(!tail.compareAndSet(tailNode, newTail));
    }

    @Override
    public int Deq() {
        QueueNode headNode = head.get();
        do
        {
            headNode = head.get();
            if(headNode == null)
                return -1;

        }while(!head.compareAndSet(headNode, headNode.next.get()));
        return headNode.val;
    }

    private class QueueNode{
        int  val;
        public AtomicReference<QueueNode> next;

        public QueueNode(int val) {
            this.val = val;
            this.next = new AtomicReference<>(null);
        }
    }
}