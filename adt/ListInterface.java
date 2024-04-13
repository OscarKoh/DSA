package adt;

import java.util.Iterator;

public interface ListInterface<T> {

    public void add(T newItem);

    public T remove(int index);

    public void amend(int index, T newItem);

    public int search(T item);

    public Iterator<T> getIterator();

    public void mergeLists(ListInterface<T> anotherList);

    public boolean isEmpty();

    public boolean isFull();

    public int size();

    public T get(int index);

    public void reverse();

    public void clear();

    public T[] toArray();

    public void sort();

    public void sort(String... atributeNames);

    public void swap(int firstIndex, int secIndex);

}
