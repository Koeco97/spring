package test.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import test.FoodType;
import test.dto.Food;
import test.service.ZooService;

import java.time.LocalDateTime;

@Service
public class EventService {

    @EventListener(ZooEvent.class)
    public void onApplicationEvent(ZooEvent zooEvent) {
        System.out.println(zooEvent.getMessage());
    }

    @EventListener(FeedEvent.class)
    public void onApplicationEvent(FeedEvent feedEvent) {
        System.out.println(feedEvent.getMessage());
        ZooService service = feedEvent.getContext().getBean(ZooService.class);
        service.feedAllHungry();
    }
}
