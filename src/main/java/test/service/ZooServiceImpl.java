package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import test.Animal;
import test.Zoo;
import test.dto.Food;
import test.event.FeedEvent;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooServiceImpl implements ZooService {
    private final Zoo zoo;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public ZooServiceImpl(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public void feed(Food food) {
        System.out.println("try feed with " + food.getFoodName());
        List hungryAnimals = zoo.getAnimals()
                .stream()
                .filter(Animal::isHungry)
                .peek(animal -> animal.eat(food))
                .collect(Collectors.toList());
        System.out.println("hungry: " + hungryAnimals);
        if(!(hungryAnimals.isEmpty())) {
            publisher.publishEvent(new FeedEvent());
        }
    }
}
