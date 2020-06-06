package test.dto;
import test.FoodType;
import java.time.LocalDateTime;
import java.util.Random;


public class Food {
    private FoodType foodName;
    private LocalDateTime expirationDate;

    public FoodType getFoodName() {
        return foodName;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setFoodName(FoodType foodName) {
        this.foodName = foodName;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public FoodType getRandomFood (){
        int pick = new Random().nextInt(FoodType.values().length);
        return FoodType.values()[pick];
    }
}
