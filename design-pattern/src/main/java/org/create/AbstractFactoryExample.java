package org.create;

public class AbstractFactoryExample {
    public static void main(String[] args) {
        FurnitureFactory factory1 = new ModernFurnitureFactory();
        FurnitureFactory factory2 = new VictorianFurnitureFactory();

        Chair chair1 = factory1.createChair();
        Sofa sofa1 = factory1.createSofa();

        chair1.sitOn();
        sofa1.lieOn();

        Chair chair2 = factory2.createChair();
        Sofa sofa2 = factory2.createSofa();

        chair2.sitOn();
        sofa2.lieOn();


    }
}

interface Chair {
    void sitOn();
}

interface Sofa {
    void lieOn();
}

class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("VictorianChair - SitOn");
    }
}

class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("ModernChair - SitOn");
    }
}

class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("VictorianSofa - LieOn");
    }
}

class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("ModernSofa - LieOn");
    }
}

interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}

class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}

class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}
