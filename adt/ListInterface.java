package adt;

import java.util.Iterator;
import java.util.Set;

/**
 * ListInterface.java An interface for the ADT List. Entries in the list have
 * positions that begin with 1.
 *
 * @author Frank M. Carrano
 * @version 2.0
 */
public interface ListInterface<T> {

    /**
     * Task: Adds a new entry to the end of the list. Entries currently in the
     * list are unaffected. The list's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if the list is full
     */
    public boolean add(T newEntry);

    public void amend(int index, T newItem);

    public T remove(int index);

    public boolean remove(T element);

    /**
     * Task: Removes all entries from the list.
     */
    public void clear();

    /**
     * Task: Replaces the entry at a given position in the list.
     *
     * @param givenPosition an integer that indicates the position of the entry
     * to be replaced
     * @param newEntry the object that will replace the entry at the position
     * givenPosition
     * @return true if the replacement occurs, or false if either the list is
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    public boolean replace(int givenPosition, T newEntry);

    /**
     * Task: Retrieves the entry at a given position in the list.
     *
     * @param givenPosition an integer that indicates the position of the
     * desired entry
     * @return a reference to the indicated entry or null, if either the list is
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    public T getEntry(int givenPosition);

    /**
     * Task: Sees whether the list contains a given entry.
     *
     * @param anEntry the object that is the desired entry
     * @return true if the list contains anEntry, or false if not
     */
    public boolean contains(T anEntry);

    public boolean isFull();

    public void mergeSets(Set<T> anotherSet);

    public T[] toArray();

    public void generateSummaryReports();

    /**
     * Task: Sees whether the list is empty.
     *
     * @return true if the list is empty, or false if not
     */
    public boolean isEmpty();

    public int search(T item);

    public T get(int index);

    public int indexOf(T element);

    public int size();

    public Iterator<T> getIterator();
}