package SECTION_1;


import INTERFACES.Queue;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentQueue implements Queue {

    private final Node dummy = new Node(null);
    private final AtomicReference<Node> head = new AtomicReference<Node>(dummy);
    private final AtomicReference<Node> tail = new AtomicReference<Node>(dummy);

    public void Enq(final Integer item) {

        final Node  newNode = new Node(item);
        boolean successToAdd;
        boolean successToUpdateTail;
        do{
            Node curTail = tail.get();
            successToAdd = curTail.next.compareAndSet(null, newNode);
            successToUpdateTail = tail.compareAndSet(curTail, curTail.next.get());
        }while (successToAdd && successToUpdateTail);
    }


    @Override
    public Integer Deq() {
        final Node dummy = head.get();
        Node oldHead;
        Node newHead;
        do {
            oldHead = dummy.next.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next.get();
        } while (!head.get().next.compareAndSet(oldHead,newHead));
        return oldHead.val;
    }

    public int Count() {
        AtomicReference<Node> curr = head.get().next;
        int count = 0;
        while (curr.get() != null){
            count++;
            curr= curr.get().next;
        }
        return count;
    }

    private class Node {
        final Integer val;
        final AtomicReference<Node> next;

        public Node(Integer value) {
            val = value;
            next = new AtomicReference<Node>(null);
        }
    }
}