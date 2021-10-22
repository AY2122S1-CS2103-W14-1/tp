package seedu.address.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * EntryList is an abstract class that uses only three main functions of a list - add, delete, sort
 */
public abstract class EntryList<Entry> {
    private ArrayList<Entry> list = new ArrayList<>();

    public boolean add(Entry elem) {
        return list.add(elem);
    }

    public Entry delete(int i) {
        return list.remove(i);
    }

    public void sort(Comparator<? super Entry> c) {
        list.sort(c);
    }

}
