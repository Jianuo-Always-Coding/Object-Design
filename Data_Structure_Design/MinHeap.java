import java.util.Arrays;

public class MinHeap {
    private int[] array;
    private int size;

    public MinHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("input array can not be null or empty!");
        }
        this.array = array;
        this.size = array.length;
        heapify();
    }

    public MinHeap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Capacity can not be <= 0!");
        }
        this.array = new int[cap];
        this.size = 0;
    }

    // Do percolate down from index to 0
    private void heapify() {
        int start = size / 2 - 1;

        for (int i = start; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int index) {
        // corner case
        if (size <= 1) {
            return ;
        }

        while (index < size / 2) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            int candidateIndex = leftIndex;
            if (rightIndex < size && array[leftIndex] > array[rightIndex]) {
                candidateIndex = rightIndex;
            }

            
            if (array[index] > array[candidateIndex]) {
                swap(index, candidateIndex);
                index = candidateIndex;
            } else {
                break;
            }
        }
    }

    private void percolateUp(int index) {
        // corner case
        if (size <= 1) {
            return;
        }

        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (array[index] < array[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return array[0];
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }

        Integer result = array[0];
        array[0] = array[size - 1];
        size--;
        percolateDown(0);
        return result;
    }

    public void offer(int ele) {
        if (isFull()) {
            array = Arrays.copyOf(array, (int)(array.length * 1.5));
        } 

        array[size] = ele;
        size++;
        percolateUp(size - 1);
    }

    public int update(int index, int ele) {
        // corner case
        if (index >= size) {
            throw new IllegalArgumentException("index is out of bound!");
        }

        int result = array[index];
        array[index] = ele;
        if (ele > result) {
            percolateDown(index);
        } else {
            percolateUp(index);
        }
        return result;
    }
    
}