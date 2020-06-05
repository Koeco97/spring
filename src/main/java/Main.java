import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.FoodType;
import test.configuration.AnnotationConfiguration;
import test.dto.Food;
import test.event.FeedEventPublisher;
import test.service.ZooService;

import java.time.LocalDateTime;

public class Main {
//    public static void main(String[] args) {
//        Zoo zoo = new Zoo(new Dog(), new Cat());
//        zoo.getCat().voice();
//        zoo.getDog().voice();
//    }

    public static void main(String[] args) {
        ApplicationContext context = getAnnotationContext();
        feedAnimal(context);
        FeedEventPublisher feedEventPublisher = (FeedEventPublisher) context.getBean("publisher", context);
        feedEventPublisher.publish("FeedEvent");
    }

    public static void feedAnimal(ApplicationContext context) {
        ZooService service = context.getBean(ZooService.class);
        Food food = new Food();
        food.setFoodName(FoodType.MEAT);
        food.setExpirationDate(LocalDateTime.now().plusHours(6));
        service.feed(food);
    }

    public static ApplicationContext getAnnotationContext() {
        return new AnnotationConfigApplicationContext(AnnotationConfiguration.class);
    }
}
