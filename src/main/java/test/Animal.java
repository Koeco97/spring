package test;

import test.dto.Food;

import java.util.List;

public interface Animal {
    void voice();

    boolean eat(Food food);

    boolean isHungry();

    List<FoodType> getPossibleFeedTypes();

    boolean canEat(Animal animal, Food food);
}
