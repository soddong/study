## Adapter Pattern
* 서로 다른 인터페이스를 가진 클래스들을 연결하기 위해 중간에 어댑터를 두는 패턴
* 기존 클래스를 수정하지 않고 새로운 코드와 호환할 수 있다

### 코드 구성 요소 & 역할

| 요소        | 역할                                              |
|-------------|---------------------------------------------------|
| `Target`    | 클라이언트가 기대하는 인터페이스                          |
| `Adaptee`   | 기존 클래스 (인터페이스가 다름)                           |
| `Adapter`   | Target을 구현하고 내부에서 Adaptee를 호출하여 변환 역할 수행 |
| `Client`    | Target을 사용하며, Adapter를 통해 Adaptee를 간접 사용      |

```java
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
```
* 기존 시스템은 RoundPeg라는 클래스(원형 못)를 기준으로 설계되어 있고
* SquarePeg(네모 못)를 끼워야 하는 상황이 생겼 을 때,
* 이 어댑터 클래스는 SquarePeg를 감싸고 있으며,
* 기존 시스템이 기대하는 getRadius() 메서드를 간접적으로 계산해 제공한다.
* 이렇게 하면 SquarePeg 객체를 RoundPeg처럼 사용할 수 있게 된다!
---

## Bridge Pattern
* 기능 계층(Abstraction)과 구현 계층(Implementor)을 분리하여 서로 독립적으로 확장할 수 있는 구조
* 기능과 구현을 조합해서 사용하되, 두 계층을 따로 관리할 수 있으므로 클래스 수가 불필요하게 늘어나지 않음
* 아래 그림을 보자.
![img_1.png](bridge.png)
  * “모양”과 “색상”이라는 두 개의 독립적인 계층이 있다고 할 때,
  * 각각을 분리해서 구현하면 모든 조합(빨간 원, 파란 원, 빨간 사각형, 파란 사각형)에 대한 클래스를 다 만들 필요 없이,
  * 모양 클래스에서 색상 구현체를 참조하는 방식으로 조합 가능함

### 구조
![img.png](bridge2.png)
### 코드 구성 요소 & 역할
| 요소               | 클래스명          | 역할                                                    |
|------------------|---------------|---------------------------------------------------------|
| Abstraction      | `Remote`      | 기능 계층의 추상화. 디바이스 제어 메서드 정의           |
| RefinedAbstraction | - (패스...)     | 기능 계층 확장 클래스          |
| Implementor      | `Device`      | 구현 계층 인터페이스. 실제 기기 조작 인터페이스 정의    |
| ConcreteImplementor | `Radio`, `TV` | 실제 기기 구현 |

---

## Composite Pattern
* 객체들을 트리 구조로 구성하고, 공통 인터페이스(Component)를 통해  
  부분(Leaf)과 전체(Composite)를 동일한 타입으로 다룰 수 있게 해주는 패턴   
-> 이를 통해 구조를 단순화하고, 클라이언트 코드의 복잡도를 줄임

### 구조
![img_1.png](composite.png)

### 코드 구성 요소 & 역할

| 구성요소     | 클래스명           | 설명                                            |
|--------------|--------------------|-------------------------------------------------|
| Component    | `Graphic`          | 공통 인터페이스                                  |
| Leaf         | `Dot`, `Circle`    | 실제 기능 수행, 복잡한 구조 없음                 |
| Composite    | `CompoundGraphic`  | 자식 리스트를 보유하며 재귀적으로 위임 처리     |
| Client       | `ImageEditor`      | 구조를 몰라도 일괄적으로 작업 수행 가능 | 

* Graphic 이라는 공통 인터페이스를 통해 Dot, Circle, CompoundGraphic을 동일한 타입으로 다룬다.
* CompoundGraphic은 Graphic 타입의 자식들을 리스트 형태로 가지고
* 내부 요소가 Dot이든, Circle이든, 또 다른 CompoundGraphic이든 관계없이
* move, draw를 재귀적으로 호출할 수 있다.

* 즉, ImageEditor는 전체 구조가 leaf인지 composite인지 신경 쓰지 않고
* Graphic 타입으로 요소를 추가, 그룹화, 이동, 그리기를 수행한다.