package org.struct;

import java.util.ArrayList;
import java.util.List;

public class CompositeExample {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();
        editor.load(); // Dot, Circle draw

        // 선택
        Dot dot1 = new Dot(10, 10);
        Circle circle1 = new Circle(20, 20, 5);
        editor.getAll().add(dot1);
        editor.getAll().add(circle1);

        // 이 두 개를 그룹으로 묶음
        List<Graphic> selected = new ArrayList<>();
        selected.add(dot1);
        selected.add(circle1);
        editor.groupSelected(selected); // 그룹화 후 draw

        // 전체 이동 후 다시 draw
        System.out.println("== 전체 이동 후 다시 그리기 ==");
        editor.getAll().move(100, 200);
        editor.getAll().draw();
    }
}

// 공통 인터페이스
interface Graphic {
    void move(int x, int y);
    void draw();
}

// Leaf: 점
class Dot implements Graphic {
    protected int x;
    protected int y;

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        System.out.println("dot move to (" + this.x + ", " + this.y + ")");
    }

    @Override
    public void draw() {
        System.out.println("draw dot at (" + x + ", " + y + ")");
    }
}

// Leaf 확장: 원
class Circle extends Dot {
    private int radius;

    Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("draw circle at (" + x + ", " + y + ") with radius " + radius);
    }
}

// Composite
class CompoundGraphic implements Graphic {
    private final List<Graphic> children = new ArrayList<>();

    void add(Graphic child) {
        children.add(child);
    }

    void remove(Graphic child) {
        children.remove(child);
    }

    @Override
    public void move(int x, int y) {
        for (Graphic g : children) {
            g.move(x, y);
        }
    }

    @Override
    public void draw() {
        for (Graphic g : children) {
            g.draw();
        }
    }
}

// Client
class ImageEditor {
    private final CompoundGraphic all = new CompoundGraphic();

    void load() {
        all.add(new Dot(1, 2));
        all.add(new Circle(5, 3, 10));
        System.out.println("== 초기 로딩 ==");
        all.draw();
    }

    void groupSelected(List<Graphic> components) {
        CompoundGraphic group = new CompoundGraphic();
        for (Graphic g : components) {
            group.add(g);
            all.remove(g);
        }
        all.add(group);
        System.out.println("== 그룹핑 후 ==");
        all.draw();
    }

    CompoundGraphic getAll() {
        return all;
    }
}
