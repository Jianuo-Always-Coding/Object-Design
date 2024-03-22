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

