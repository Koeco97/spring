package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.ArrayList;
import java.util.List;

@Component
public class Snake implements Animal{
    private boolean hungry = true;

    @Override
    public void voice() {
        System.out.println("sss");
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
        possibleFood.add(FoodType.MOUSE);
        return possibleFood;
    }
}
