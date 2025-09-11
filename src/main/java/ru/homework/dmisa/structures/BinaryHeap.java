package ru.homework.dmisa.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T> {

    private final List<T> array;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.array = new ArrayList<>();
        this.comparator = comparator;
    }

    public BinaryHeap(List<T> array, Comparator<T> comparator) {
        this.array = array;
        this.comparator = comparator;
        buildHeap();
    }

    public T getParent(int i) {
        return array.get(getParentIndex(i));
    }

    public T getRight(int i) {
        return array.get(getRightIndex(i));
    }

    public T getLeft(int i) {
        return array.get(getLeftIndex(i));
    }

    public T getMinAndRemove() {
        T rootValue = array.getFirst();
        array.set(0, array.getLast());
        array.removeLast();
        shiftDown(0);
        return rootValue;
    }

    public void add(T value) {
        array.add(value);
        shiftUp(array.size() - 1);
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private void shiftDown(int i) {
        while (getLeftIndex(i) < array.size()) {
            int minIndex = getLeftIndex(i);
            if (getRightIndex(i) < array.size() && comparator.compare(getRight(i), getLeft(i)) < 0) {
                minIndex = getRightIndex(i);
            }
            if (comparator.compare(array.get(i), array.get(minIndex)) > 0) {
                swapElements(i, minIndex);
                i = minIndex;
            }
        }
    }

    private void shiftUp(int i) {
        while (i > 0 && comparator.compare(array.get(i), getParent(i)) < 0) {
            swapElements(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    private void buildHeap() {
        for (int i = array.size() / 2; i >= 0; --i) {
            shiftDown(i);
        }
    }

    private void swapElements(int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
