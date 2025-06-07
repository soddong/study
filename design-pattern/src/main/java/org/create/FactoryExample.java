package org.create;

public class FactoryExample {
    public static void main(String[] args) {
        Logistics road = new RoadLogistics();
        road.planDelivery();

        Logistics sea = new SeaLogistics();
        sea.planDelivery();
    }
}

interface Transport {
    void deliver();
}

class Truck implements Transport {
    public void deliver() {
        System.out.println("Truck Deliver");
    }
}

class Ship implements Transport {
    public void deliver() {
        System.out.println("Ship Deliver");
    }
}

abstract class Logistics {
    abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

class RoadLogistics extends Logistics {
    Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    Transport createTransport() {
        return new Ship();
    }
}
