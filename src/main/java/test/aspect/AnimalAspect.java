package test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import test.Animal;
import test.FoodType;
import test.dto.Food;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class AnimalAspect {

    //Условие выборки методов: Выбирается метод test.Animal.eat
    //Возвращаемый тип, а также тип и количество аргументов не важны.
    @Pointcut("execution(* test.Animal.eat(..))")
    public void eatPoint() {
    }

    @Pointcut("execution(* test.Animal.getPossibleFeedTypes(..))")
    public void foodTypePoint() {
    }

    //Условие выборки класса: отбирается класс test.Fish
    @Pointcut("within(test.Fish)")
    public void fishPoint() {
    }

    //выполняется до вызова метода
    @Before(value = "eatPoint()")
    public void beforeEat() {
        System.out.println("start eat");
    }

    //выполняется после вызова метода
    @After(value = "eatPoint()")
    public void afterEat() {
        System.out.println("end eat");
    }

    //выполняется, если выброшено исключение
    @AfterThrowing(value = "eatPoint()", throwing = "ex")
    public void eatFailed(Throwable ex) {
        System.out.println("eat failed: " + ex.getMessage());
    }

    //выполняется при нормальном завершении метода
    @AfterReturning(value = "eatPoint()")
    public void eatSuccess(JoinPoint joinPoint) {
        System.out.println("eat success");
    }

    //действие и до и после
    //При любом вызове test.Animal.eat с входящим аргументом food для всех классов, кроме test.Fish
    //Создается точка входа
    //Если ожидаемая дата позже текущей, возвращаем false
    //печатаем текущий класс + " start eat"
    //пробуем выполнить метод eat c аргументом food для текущего класса.
    // Если метод выполняется пишем класс + " eat success" и класс + " end eat"
    //Если метод не выполняется, пишем класс + " eat failed: " +
    @Around(value = "eatPoint() && args(food) ")
    public Object eatAround(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        String target = proceedingJoinPoint.getTarget().getClass().toString();
        if (LocalDateTime.now().isAfter(food.getExpirationDate())){
            return false;
        }
       /* List possibleFood = animal.getPossibleFeedTypes();
        if (possibleFood.contains(food)) {
            System.out.println(target + " start eat");*/
            try {
                Object result = proceedingJoinPoint.proceed();
                System.out.println(target + " eat success");
                System.out.println(target + " end eat");
                return result;
            } catch (Throwable e) {
                System.out.println(target + " eat failed: " + e.getMessage());
                throw e;
            }
        }


   /* @Around(value = "eatPoint() && args(food) && fishPoint()")
    public Object validateEatForFish(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        if (food.getFoodName().equals(FoodType.FISH)) {
            return false;
        } else {
            return eatAround(proceedingJoinPoint, food);
        }
    }*/
    @Around(value = "foodTypePoint() && args(animal) && args(food)", argNames = "proceedingJoinPoint,animal,food")
    public Object validateEat(ProceedingJoinPoint proceedingJoinPoint, Animal animal, Food food) throws Throwable {
       List possibleFood = animal.getPossibleFeedTypes();
       if (possibleFood.contains(food))
           return true;
       else
           return false;
    }
}
