package SECTION_1;


import INTERFACES.Queue;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentQueue implements Queue {

    private final Node dummy = new Node(null);
    protected final AtomicReference<Node> head = new AtomicReference<Node>(dummy);
    protected final AtomicReference<Node> tail = new AtomicReference<Node>(dummy);

    public void Enq(final Integer item) {

        final Node  newNode = new Node(item);
        boolean successToAdd;
        boolean successToUpdateTail;
        do{
            Node curTail = tail.get();
            successToAdd = curTail.next.compareAndSet(null, newNode);
            successToUpdateTail = tail.compareAndSet(curTail, curTail.next.get());
        }while (!successToAdd /*|| !successToUpdateTail*/);
    }


    @Override
    public Integer Deq() {
        try {
            final Node dummy = head.get();
            AtomicReference<Node> oldHead;
            Node newHead;
            do {
                oldHead = dummy.next;
                if (oldHead.get() == null)
                    return null;
                newHead = oldHead.get().next.get();
            } while (!oldHead.compareAndSet(oldHead.get(),newHead));
            return oldHead.get().val;
        }catch (NullPointerException exn){
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

    protected class Node {
        public final Integer val;
        public final AtomicReference<Node> next;

        public Node(Integer value) {
            val = value;
            next = new AtomicReference<Node>(null);
        }
    }
}