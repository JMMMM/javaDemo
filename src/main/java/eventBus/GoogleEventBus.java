package eventBus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * eventBus
 *
 * @author jimmy
 * @date 2019-05-13
 */
public class GoogleEventBus {
    private static EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        GoogleEventBusPub googleEventBusPub = new GoogleEventBusPub(eventBus);
        GoogleEventBusProcessor googleEventBusSub = new GoogleEventBusProcessor();
        googleEventBusPub.register(googleEventBusSub);

        Event event = new Event("Hello World");
        googleEventBusPub.sendMessage(event);
    }
}

class IdProvider {
    private static AtomicInteger id = new AtomicInteger(1);

    public static int getId() {
        return id.getAndIncrement();
    }
}

class Event {
    private int id;
    private int topic;
    private String message;

    public Event(String message) {
        this.message = message;
        this.id = IdProvider.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}

/**
 * 消息发布者
 */
class GoogleEventBusPub {
    private final EventBus eventBus;

    public GoogleEventBusPub(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void sendMessage(Event event) {
        this.eventBus.post(event);
    }

    public void register(Object object) {
        this.eventBus.register(object);
    }
}

/**
 * 时间处理器
 */
class GoogleEventBusProcessor {

    @Subscribe
    public void getMessage(Event event) {
        System.out.println(event.toString());
    }

}