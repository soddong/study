package org.struct;

public class DecoratorExample {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();       // 기본 커피 3000
        coffee = new Milk(coffee);               // + 우유 500 → 3500
        coffee = new Syrup(coffee);              // + 시럽 300 → 3800

        System.out.println("Total Cost: " + coffee.cost());
    }
}

// 상위 인터페이스
interface Beverage {
    int cost();
}

// 기본 커피
class Coffee implements Beverage {
    public int cost() {
        System.out.println(">> Coffee cost() method call, 3000");
        return 3000;
    }
}

// 공통 데코레이터 추상 클래스
abstract class AddOnDecorator implements Beverage {
    protected Beverage base;

    public AddOnDecorator(Beverage base) {
        this.base = base;
    }
}

// 우유 추가
class Milk extends AddOnDecorator {
    public Milk(Beverage base) {
        super(base);
    }

    public int cost() {
        System.out.println(">> Milk cost() method call, +500");
        return base.cost() + 500;
    }
}

// 시럽 추가
class Syrup extends AddOnDecorator {
    public Syrup(Beverage base) {
        super(base);
    }

    public int cost() {
        System.out.println(">> Syrup cost() method call, +300");
        return base.cost() + 300;
    }
}