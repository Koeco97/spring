package test.dto;
import test.FoodType;
import java.time.LocalDateTime;


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
}
