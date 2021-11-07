package seedu.docit.model;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * EntryList is a class that uses only four main functions of a list - add, delete, size, get, toStream
 */
public class EntryList<Entry> {
    private ArrayList<Entry> list = new ArrayList<>();

    public boolean add(Entry elem) {
        return list.add(elem);
    }

    public Entry delete(int i) {
        return list.remove(i);
    }

    public int size() {
        return list.size();
    }

    public Entry get(int i) {
        return list.get(i);
    }

    public Stream<Entry> toStream() {
        return list.stream();
    }
}
