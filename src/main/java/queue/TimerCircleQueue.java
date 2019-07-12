package queue;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 环形队列
 * AtomicInteger会出现过大溢出的可能性，不能持续运行
 * 于是当currentIndex到达边界的时候 重设currentIndex的值
 *
 * @param <E>
 */
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
                int _curr = currentIndex.incrementAndGet();
                if (_curr == size) currentIndex.set(0);
                int currIndex = _curr % size;
                nowSlot = elements.remove(currIndex);
                slot = new Slot<>();
                elements.put(currIndex, slot);
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

