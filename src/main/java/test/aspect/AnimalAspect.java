package test.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import test.Animal;
import test.dto.Food;
import java.time.LocalDateTime;

@Aspect
@Component
public class AnimalAspect {

    @Pointcut("execution(* test.Animal.eat(..))")
    public void eatPoint() {
    }

    @Around(value = "eatPoint() && args(food)")
    public Object eatAround(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        String target = proceedingJoinPoint.getTarget().getClass().toString();
        Animal animal = (Animal) proceedingJoinPoint.getTarget();
        if (LocalDateTime.now().isAfter(food.getExpirationDate())) {
            return false;
        }
        if (animal.getPossibleFeedTypes().contains(food.getFoodName())) {
            try {
                Object result = proceedingJoinPoint.proceed();
                System.out.println(target + " eat success");
                return result;
            } catch (Throwable e) {
                System.out.println(target + " eat failed: " + e.getMessage());
                throw e;
            }
        }
        else
            return false;
    }
}
