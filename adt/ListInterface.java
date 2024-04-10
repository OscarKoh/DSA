package adt;

import java.util.Iterator;

public interface ListInterface<T> {

    public void add(T newItem);

    public T remove(int index);

    public void amend(int index, T newItem);

    public int search(T item);

    public Iterator<T> getIterator();

    public void mergeSets(ListInterface<T> anotherSet);

    public boolean isEmpty();

    public int size();

    public T get(int index);

    public void clear();

    public T[] toArray();

    public void sort();

    public void sort(String... atributeNames);

    public void swap(int firstIndex, int secIndex);

}
