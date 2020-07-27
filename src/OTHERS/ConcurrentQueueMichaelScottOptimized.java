package OTHERS;

import SECTION_1.ConcurrentQueue;

public class ConcurrentQueueMichaelScottOptimized extends ConcurrentQueue {

    @Override
    public void Enq(Integer item) {
        Node newNode = new Node(item); //new node created

        while (true) {
            Node curTail = tail.get(); //"tail" value is read
            Node tailNext = curTail.next.get();//next of "tail"

            if (curTail == tail.get()) {
                if (tailNext != null) { // queue in intermediate state...
                    tail.compareAndSet(curTail, tailNext); //advance tail
                } else { // queue in quiescent state...
                    // try inserting the new node
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);
                        return;
                    }
                }
            }
        }
    }

//    @Override
//    public Integer Deq() {
//        Node oldHead;
//        Node newHead;
//        do {
//            oldHead = head.get().next.get();
//            if (oldHead == null)
//                return null;
//            newHead = oldHead.next.get();
//        } while (!head.get().next.compareAndSet(oldHead,newHead));
//        return oldHead.val;
//    }
//        while (true){
//            Node first = head.get();
//            Node last = tail.get();
//            Node next = first.next.get();
//            if (first == head.get()){
//                if (next == null){
//                    return  null;
//                }
//                tail.compareAndSet(last, next);
//            }
//            else{
//                Integer item =  next.val;
//                if (head.compareAndSet(first , next)){
//                    return item;
//                }
//            }
//        }
//    }
}
