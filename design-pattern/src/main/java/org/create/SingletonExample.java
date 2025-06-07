package org.create;

public class SingletonExample {
    public static void main(String[] args) {

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();

        System.out.println(s1 + ", " + s2 + ", " + s3);
    }
}

class Singleton {

    private static final Singleton singleton = new Singleton();
    public static Singleton getInstance() {
        return singleton;
    }
}
