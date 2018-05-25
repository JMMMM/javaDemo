package queue;


import javafx.scene.shape.Circle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerCircleQueue<E> {
    transient int size = 0;

    private ConcurrentHashMap<Integer, Slot<E>> elements;

    private Slot<E> nowSlot;

    private Slot<E> slot;

    private AtomicInteger currentIndex = new AtomicInteger(0);

    private static class Slot<E> {
        LinkedList<E> elements;

        public Slot() {
            this.elements = new LinkedList();
        }
    }

    private int tick;

    public TimerCircleQueue(int size, int tick) {
        this.size = size;
        this.tick = tick;
        elements = new ConcurrentHashMap<>(size);
    }


    public void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("前进一格");
                int nowIndex = currentIndex.incrementAndGet() % size;
                nowSlot = elements.remove(nowIndex);
                slot = new Slot<>();
                elements.put(nowIndex, slot);
            }
        }, tick);
    }

    public List<E> get() {
        return nowSlot.elements;
    }

    public void add(E element) {
        slot.elements.add(element);
    }
}

