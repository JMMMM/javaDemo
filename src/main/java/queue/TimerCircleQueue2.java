package queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCircleQueue2<E> {

    private static class Node<E> {
        Slot<E> slot;
        Node<E> next;

        Node() {
            this.slot = new Slot<>();
        }
    }

    private static class Slot<E> {
        LinkedList<E> elements;

        public Slot() {
            this.elements = new LinkedList();
        }
    }

    private Slot<E> waitingSlot;
    private Slot<E> processingSlot;

    private Node<E> first;
    private int size = 0;
    private int tick = 0;
    private Node<E> currNode;

    public TimerCircleQueue2(int size, int tick) {
        this.size = size;
        this.tick = tick;
        first = new Node<>();
        Node<E> prev = first;
        for (int i = 1; i < size; i++) {
            Node<E> curr = new Node<>();
            prev.next = curr;
            prev = curr;
        }
        prev.next = first;//环形
        currNode = first;
    }

    public void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                currNode = currNode.next;
                processingSlot = currNode.slot;
                waitingSlot = new Slot<>();
                currNode.slot = waitingSlot;
            }
        }, tick);
    }

    public List<E> get() {
        return processingSlot.elements;
    }

    public void add(E element) {
        waitingSlot.elements.add(element);
    }
}
