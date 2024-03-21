# Object-Design

Data Structure and Object Oriented Design. And error prone points.

## MinHeap Design

When do percolate down, be careful of when the node only has a left child, and doesn't have a right child.

> **How to correct this?** Use a candidate variable.

How to expand an array?

> ```java
> array = Arrays.copyOf(array, (int)(array.length \* 1.5));
> ```
