package org.struct;

public class AdapterExample {

    public static void main(String[] args) {
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        System.out.println(roundHole.fits(roundPeg)); // true

        RoundHole roundHole2 = new RoundHole(5);
        RoundPeg roundPeg2 = new RoundPeg(10);
        System.out.println(roundHole2.fits(roundPeg2)); // false

        RoundHole roundHole3 = new RoundHole(5);
        SquarePeg largeSquarePeg = new SquarePeg(10);
        SquarePegAdapter adapter = new SquarePegAdapter(largeSquarePeg);
        System.out.println(roundHole3.fits(adapter)); // false
    }
}

class RoundHole {
    private final int radius;

    RoundHole(int radius) {
        this.radius = radius;
    }

    int getRadius() {
        return radius;
    }

    boolean fits(RoundPeg peg) {
        return peg.getRadius() <= radius;
    }
}

class RoundPeg {
    private final int radius;

    RoundPeg(int radius) {
        this.radius = radius;
    }

    int getRadius() {
        return radius;
    }
}

class SquarePeg {
    private final int width;

    SquarePeg(int width) {
        this.width = width;
    }

    int getWidth() {
        return width;
    }
}

class SquarePegAdapter extends RoundPeg {
    private final SquarePeg peg;

    SquarePegAdapter(SquarePeg peg) {
        super(0);
        this.peg = peg;
    }

    @Override
    int getRadius() {
        return (int) (peg.getWidth() * Math.sqrt(2) / 2);
    }
}
