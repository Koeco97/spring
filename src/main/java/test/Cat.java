package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cat implements Animal {
    private boolean hungry = true;
    private List possibleFood = getPossibleFeedTypes();

    public void voice() {
        System.out.println("mi");
    }

    @Override
    public boolean eat(Food food) {
        hungry = false;
        return isHungry();
    }

    @Override
    public boolean isHungry() {
        return hungry;
    }

    @Override
    public List<FoodType> getPossibleFeedTypes() {
        List <FoodType> possibleFood = new ArrayList<>();
        possibleFood.add(FoodType.FISH);
        possibleFood.add(FoodType.MILK);
        possibleFood.add(FoodType.MOUSE);
        possibleFood.add(FoodType.MEAT);
        return possibleFood;
    }

    @Override
    public boolean canEat (Animal animal, Food food){
        return animal.getPossibleFeedTypes().contains(food.getFoodName());
    }
}
