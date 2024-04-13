package adt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements ListInterface<T>, Serializable {

    private static final int DEFAULT_CAPACITY = 3;
    private T[] array;
    private int size;

    //Constructor 
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    //Constructor
    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    //Utility methods
    //Check if the array can add in one more element 
    private void ensureCapacity(int minCapacity) {
        //Resize the array if not enough size 
        if (minCapacity > array.length) {
            int newCapacity = Math.max(array.length * 2, minCapacity);
            T[] newArray = (T[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    //Utility methods
    //similar to make room 
    private void shiftLeft(int fromIndex) {
        // Copy elements starting from fromIndex+1 to size-1 to positions fromIndex to size-2
        System.arraycopy(array, fromIndex + 1, array, fromIndex, size - fromIndex - 1);
        // Set the last element to null to avoid memory leak
        array[--size] = null;
    }

    @Override
    public void add(T newItem) {
        ensureCapacity(size + 1);

        //Add the new item into the array first
        //Then only the size is incremented 
        array[size++] = newItem;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T removedItem = array[index];
        shiftLeft(index);
        return removedItem;
    }

    @Override
    public void amend(int index, T newItem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = newItem;
    }

    @Override
    public int search(T item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> getIterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                //print the eleemnt at the current index
                //Then increment current Index 
                return array[currentIndex++];
            }
        };
    }

    @Override
    public void mergeLists(ListInterface<T> anotherList) {
        Iterator<T> iterator = anotherList.getIterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (search(item) == -1) {
                add(item);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    @Override
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            swap(i, size - i - 1);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

//    public void sort() {
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = 0; j < size - i - 1; j++) {
//                // Compare adjacent elements and swap if necessary
//                if (((Comparable<T>) array[j]).compareTo(array[j + 1]) > 0) {
//                    T temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                }
//            }
//        }
//    }
    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void sort() {
        quickSort(0, size - 1);
    }

    public void sort(String... attributeNames) {
        quickSort(0, size - 1, attributeNames);
    }

    private int partition(int low, int high) {
        // Choosing the pivot
        T pivot = array[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (((Comparable) array[j]).compareTo(pivot) <= -1) {

                // Increment index of smaller element
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    private int partition(int low, int high, String... attributeNames) {
        // Choosing the pivot
        T pivot = array[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        try {
            for (int j = low; j <= high - 1; j++) {
                // If current element is smaller than the pivot
                if ((int) (array[j].getClass().getMethod("compareTo", array[j].getClass(), String[].class).invoke(array[j], pivot, attributeNames)) <= -1) {

                    // Increment index of smaller element
                    i++;
                    swap(i, j);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
        swap(i + 1, high);
        return (i + 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private void quickSort(int low, int high, String... attributeNames) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(low, high, attributeNames);

            // Separately sort elements before
            // partition and after partition
            quickSort(low, pi - 1, attributeNames);
            quickSort(pi + 1, high, attributeNames);
        }
    }
}
