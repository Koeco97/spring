package test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Dog implements Animal {
    private boolean hungry = true;

    public void voice() {
        System.out.println("gav");
    }

    @Override
    public boolean eat(Food food) {
        hungry = false;
        return isHungry();
    }

    @Override
    public boolean isHungry() {
        return false;
    }

    @Override
    public List<FoodType> getPossibleFeedTypes() {
        List <FoodType> possibleFood = new ArrayList<>();
        possibleFood.add(FoodType.MEAT);
        return possibleFood;
    }

}
