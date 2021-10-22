package seedu.address.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * EntryList is a class that uses only four main functions of a list - add, delete, sort, size
 */
public class EntryList<Entry> {
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

    public int size() {
        return list.size();
    }

    public Entry get(int i) {
        return list.get(i);
    }
}
