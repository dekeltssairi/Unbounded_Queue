package SECTION_2;


import INTERFACES.Queue;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentQueueHelp implements Queue {

    private final Node dummy = new Node(null);
    private final AtomicReference<Node> head = new AtomicReference<Node>(dummy);
    private final AtomicReference<Node> tail = new AtomicReference<Node>(dummy);

    public void Enq(final Integer item) {

        Node  newNode = new Node(item);
        boolean success;
        do{
            Node curTail = tail.get();
            success = curTail.next.compareAndSet(null, newNode);
            tail.compareAndSet(curTail, curTail.next.get());
        }while (!success);
//        final Node  newNode = new Node(item);
//        Node curTail, residue;
//        boolean stay = true;
//        while (stay) {
//            curTail = tail.get();
//            residue = curTail.next.get();
//
//            if (curTail == tail.get()) {
//                if (residue == null) {
//                    if (curTail.next.compareAndSet(null, newNode)) {
//                        tail.compareAndSet(curTail, newNode);
//                        stay = false;
//                    }
//                } else {
//                    tail.compareAndSet(curTail, residue);
//                }
//            }
//        }
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