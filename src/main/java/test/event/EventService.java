package test.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import test.dto.Food;
import test.service.ZooService;
import java.time.LocalDateTime;

@Service
public class EventService {
    private final ZooService zooService;

    public EventService(ZooService zooService) {
        this.zooService = zooService;
    }

    @EventListener(FeedEvent.class)
    public void onApplicationEvent(FeedEvent feedEvent) {
        Food food = new Food();
        food.setFoodName(food.getRandomFood());
        food.setExpirationDate(LocalDateTime.now().plusHours(6));
        zooService.feed(food);
    }
}

