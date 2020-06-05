package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mouse implements Animal {
    private boolean hungry = true;

    @Override
    public void voice() {
        System.out.println("pi");
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
        possibleFood.add(FoodType.CORN);
        return possibleFood;

    }
}