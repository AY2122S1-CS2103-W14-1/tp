package seedu.address.model;

import seedu.address.model.person.MedicalHistory;

/**
 * Entry represents an object that is either empty or is something.
 * @param <T> type that is stored within an entry.
 */
public abstract class Entry<T> {
    private static final Empty EMPTY = new Entry.Empty();

    private Entry() {

    }

    private static <S> Entry<S> empty() {
        @SuppressWarnings("unchecked")
        Entry<S> e = (Entry<S>) EMPTY;
        return e;
    }

    private static <S> Entry<S> some(S s) {
        return new Entry.Some<S>(s);
    }

    /**
     * A factory method that returns either an Empty or Some object based on the given argument.
     *
     * @param item the item that the Entry object will store.
     * @param <S> the type of the item that will be stored in the Entry object.
     * @return an Empty object if the item is null, else a Some object that stores the value.
     */
    public static <S> Entry<S> of(S item) {
        if (item == null) {
            return Entry.empty();
        } else {
            return Entry.some(item);
        }
    }

    private static final class Empty extends Entry<Object> {
        Empty() {
            super();
        }

        @Override
        public String toString() {
            return "";
        }

        @Override
        public boolean equals(Object o) {
            return (o == EMPTY);
        }

        @Override
        public Object get() {
            return null;
        }
    }

    private static final class Some<S> extends Entry<S> {
        private final S item;


        private Some(S item) {
            super();
            this.item = item;
        }

        @Override
        public String toString() {
            return this.item.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Some<?>) {
                Some<?> some = (Some<?>) o;
                if (this.item == null) { // if null
                    return this.item == some.item;
                } else {
                    return this.item == some.item || this.item.equals(some.item);
                }
            }
            return false;
        }

        @Override
        public S get() {
            return this.item;
        }

    }

    public abstract T get();

}
