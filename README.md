# Object-Design

Data Structure and Object Oriented Design. And error prone points.

## MinHeap Design

    API: offer, peek, poll, isEmpty, isFull, size, update
    Fields: int[] array, int size
    Constructor: (array), (size)

When do percolate down, be careful of when the node only has a left child, and doesn't have a right child.

> **How to correct this?** Use a candidate variable.

How to expand an array?

> ```java
> array = Arrays.copyOf(array, (int)(array.length \* 1.5));
> ```

How to support Comparable?

> 1. array should be an Object array
> 2. All uses of the greater-than and less-than signs should be replaced with compareTo.

How to support Comparator?

> 1. Need to create a Comparator inside the MinHeap and get from the user.
> 2. All uses of the greater-than and less-than signs should be replaced with Comparator.compare(a, b).


## HashMap Design

    API: V put, V get, V remove, int size, boolean isEmpty
    Fields: Node<K, V>[] array, size, LOAD_FACTOR, capacity(The maximum number of elements to put in)
    Constructor: capacity, LOAD_FACTOR, no parameter

# Object Oriented Design
## Parking Lot 
> What's the system for?
  
    Parking. Free or not?

> How could customers use the system?

    boolean park(Vehicle v);
    boolean leave(Vehicle v);
    boolean hasSpot(Vehicle v);

> Class Design

    Vehicle, Car, Truck, ParkingSpot, Level, ParkingLot

> Design the data structure.
```java
class abstract Vehicle {
    getSize();
}

class Car extends Vehicle {
    getSize();
}

class Truck extends Vehicle {
    getSize();
}
```

```java
class ParkingSpot { // 停车位
    private final VehicleSize size;
    private Vehicle currentVehicle;

    boolean fit(Vehicle v) {}
    boolean park(Vehicle v) {}
    boolean leave(Vehicle v) {}
    Vehicle getVehicle() {}
}

class Level { // 停车场的一层
    List<ParkingSpot> spots;

    boolean hasSpot(Vehicle v) {}
    boolean park(Vehicle v) {}
    boolean leave(Vehicle v) {}
}

class ParkingLot {
    private Level[] levels;

    boolean hasSpot(Vehicle v) {}
    boolean park(Vehicle v) {}
    boolean leave(Vehicle v) {}
}
```

## Black Jack
> What's the system for?

    Playing the game between Dealer and players.

>  How to play the game?

    Initialization:
        void shuffle(); // 随机选一个和i换位置，i++
        dealCard(); //???????
        playerAction()；
        dealerAction();
        compareScore();
    
    APIs:
        playerAction: 
            hit();
            stand();
        isBlackJack();
        isBusted()
        score()
    
    Prioritization:
        shuffle(); 
        dealCard();
        playerAction();
        dealerAction();
        compareScore();
        action(): 
            hit();
            stand();
        isBlackJack();
        isBusted();
        score(); // 默认没爆掉最大

> Class Design

    Desk, Dealer, Player, Card

    Hints: Dealer 如果手牌分数小于17就继续抓

    直到这轮游戏结束，清空手牌

>Design the data structure

```java
class Desk {
    Card[] cards; // 使用array方便发牌和洗牌
    void shuffle();
    Card dealCard(); // 发牌
}

class card {
    int faceValue;
}

class Player {
    int playerId;
    List<Card> cards;
    action(); // 叫牌或者停止 hit or stand
    //在这里不要定义一个属性score，因为如果之前遇到过Ace, 无法得知分数的具体值
    int score(); // 计算手牌分数，没爆掉最大
    boolean isBlackJack();
    boolean isBusted(); // 是否超过21
}
```
