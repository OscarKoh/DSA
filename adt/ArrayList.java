package adt;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * ArrayList.java A class that implements the ADT List using an array.
 *
 * @author Frank M. Carrano
 * @version 2.0
 * @param <T>
 *
 * P4Q1)a) The Array List is not allowed to add more than the default capacity
 */
public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;
    private final static int frontIndex = 0;
    private int backIndex;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        if (numberOfEntries >= array.length) {
            DoubleArraySize();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);

        if (index != -1) { // Check if element exists in the list
            remove(index); // Call remove(int index) if element exists
            return true; // Return true to indicate successful removal
        } else {
            return false; // Return false if element does not exist in the list
        }
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public void amend(int index, T newItem) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        array[index] = newItem;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 0 || givenPosition >= numberOfEntries) {
            return null; // Return null if the given position is invalid
        }

        T removedElement = array[givenPosition]; // Store the removed element
        for (int i = givenPosition; i < numberOfEntries - 1; i++) {
            array[i] = array[i + 1]; // Shift elements to the left to close the gap
        }
        array[numberOfEntries - 1] = null; // Set the last element to null
        numberOfEntries--; // Decrease the number of entries

        return removedElement; // Return the removed element
    }

    @Override
    public void mergeSets(Set<T> anotherSet) {
        for (T item : anotherSet) {
            if (!contains(item)) {
                add(item);
            }
        }
    }

    @Override
    public T[] toArray() {
        // Create a new array of type T with the same size as the list
        T[] resultArray = Arrays.copyOf(array, numberOfEntries);
        return resultArray;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= numberOfEntries) {
            return null;
        }

        return array[index];
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    private void DoubleArraySize() {
        T[] oldArray = array;
        array = (T[]) new Object[2 * array.length];
        System.arraycopy(oldArray, 0, array, 0, oldArray.length);
    }

    @Override
    public void generateSummaryReports() {
        for (int i = 0; i < numberOfEntries; i++) {
            System.out.println("Item " + (i + 1) + ": " + array[i]);
        }
    }

    @Override
    public int search(T item) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (item.equals(array[index])) {
                return index;
            }
        }
        return -1; // Return -1 if the element is not found
    }

    @Override
    public Iterator<T> getIterator() {
        return new ArrayListIterator();
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T getEntry(int givenPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Inner class for iterator implementation
    @Override
public Iterator<T> getIterator() {
    return new ArrayListIterator();
}

// Inner class for iterator implementation
private class ArrayListIterator implements Iterator<T> {
    private int nextIndex = 0;

    @Override
    public boolean hasNext() {
        return nextIndex < numberOfEntries; // Check if there are more elements to iterate
    }

    @Override
    public T next() {
        if (hasNext()) {
            T nextEntry = array[nextIndex]; // Get the next element
            nextIndex++; // Move to the next index
            return nextEntry;
        } else {
            throw new NoSuchElementException(); // Throw exception if there are no more elements
        }
    }
}

    }
}
