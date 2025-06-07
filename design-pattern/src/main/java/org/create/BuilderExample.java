package org.create;

public class BuilderExample {
    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder builder = new CarBuilder();

        director.constructSportsCar(builder);
        Car car = builder.getProduct();

        System.out.println(car);
    }
}

class Car {
    String engine, gps;
    int seats;

    public String toString() {
        return "Car: " + seats + " seats, " + engine + ", GPS: " + gps;
    }
}

interface Builder {
    void reset();
    void setSeats(int count);
    void setEngine(String engine);
    void setGPS(String gps);
}

class CarBuilder implements Builder {
    private Car car = new Car();

    public void reset() { car = new Car(); }
    public void setSeats(int count) { car.seats = count; }
    public void setEngine(String engine) { car.engine = engine; }
    public void setGPS(String gps) { car.gps = gps; }
    public Car getProduct() { return car; }
}

class Director {
    public void constructSportsCar(Builder builder) {
        builder.reset();
        builder.setSeats(2);
        builder.setEngine("V8");
        builder.setGPS("Enabled");
    }
}

