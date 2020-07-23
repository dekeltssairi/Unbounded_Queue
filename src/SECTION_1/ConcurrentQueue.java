package SECTION_1;


import INTERFACES.Queue;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentQueue implements Queue {

    Node dummy = new Node(null);
    private AtomicReference<Node> head = new AtomicReference<Node>(dummy);
    private AtomicReference<Node> tail = new AtomicReference<Node>(dummy);

    public void Enq(Integer item) {
        Node newNode = new Node(item);
        while (true) {
            Node currentTail = tail.get();
            Node tailNext = currentTail.next.get();
            if (currentTail == tail.get()) {
                if (tailNext != null) {
                    tail.compareAndSet(currentTail, tailNext);
                } else {
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currentTail, newNode);
                        return;
                    }
                }
            }
        }
    }


    @Override
    public Integer Deq() {
        try {
            while (true) {
                Node first = head.get();
                Node last = tail.get();
                Node next = first.next.get().next.get();
                if (first == head.get()) {
                    if (first == last) {
                        if (next == null)
                            return null;
                        tail.compareAndSet(last, next);
                    } else {
                        int val = first.next.get().val;
                        if (head.get().next.compareAndSet(first.next.get(), next))
                            return val;
                    }
                }
            }
        }
        catch (NullPointerException ex){   // if empty
            return null;
        }
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
        Integer val;
        AtomicReference<Node> next;

        public Node(Integer value) {
            val = value;
            next = new AtomicReference<Node>(null);
        }
    }
}