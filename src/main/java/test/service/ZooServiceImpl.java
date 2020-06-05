package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Animal;
import test.FoodType;
import test.Zoo;
import test.dto.Food;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooServiceImpl implements ZooService {
    private final Zoo zoo;
    private List<Animal> hungryAnimals;

    @Autowired
    public ZooServiceImpl(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public void feed(Food food) {
        hungryAnimals = zoo.getAnimals()
                .stream()
                .peek(animal -> animal.eat(food))
                .filter(Animal::isHungry)
                .collect(Collectors.toList());
        System.out.println("hungry: " + hungryAnimals);
    }
    public void feedAllHungry(){
        Food food = new Food ();
        while (!(hungryAnimals.isEmpty())){
            for (FoodType foodName: FoodType.values()){
                food.setFoodName(foodName);
                food.setExpirationDate(LocalDateTime.now().plusHours(6));
                feed(food);
            }
        }
    }
}
