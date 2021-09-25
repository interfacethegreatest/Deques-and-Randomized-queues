/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

// custom iterator Array Iterator class returning an iterator.
    private class ArrayIterator implements Iterator<Item> {

        // store of the array of the custom arraylist class generated beneath this class.
        private Item[] stack;
        //count for the next place in the array.
        private int placeCount = 0;

    /**
     * constructor for storing all items in the custom ArrayList class.
     * @param items
     */
    private ArrayIterator(Item[] items) {
            stack = items;

    }

    /**
     * shuffles through the array, looking for the next non-null item.
     * @return true if remaining items after placeCount contains a non-null, else false.
     */
    public boolean hasNext() {
        //sometimes placeCount will have an edge-case outside the array,
        //catches this and returns false if already outside the array or on edge.
            try {
                for (int j = placeCount; j < stack.length; j++) {
                    if (stack[j] != null) {
                        return true;
                    }
                }
                return false;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }

    /**
     * throws exception if asked to remove from iterator.
     */
        public void remove() {
            throw new NoSuchElementException(" no remove.");
        }

    /**
     * checks the next value is not a null value, then checks that there is a next value before iterating placeCount,
     * if next is null, checks hasNext() for more values, then iterating placeCount is hasNext() is true and calling next() again,
     * if hasNext() returns false, and the item at placeCount is null, throws an exception.
     * @return's next item in the iterator
     */
        public Item next() {
            Item item = stack[placeCount];
            if (item != null) {
                if (hasNext()) {
                    placeCount++;
                }
                else {
                    placeCount++;
                }
            }
            else {
                if (hasNext()) {
                    placeCount++;
                }
                else {
                     throw new NoSuchElementException();
                }

            }
            return item;
        }
    }

// generates a custom arrayStack for the given API.
    private class ArrayStack {
        //number of values and a store of values.
        private int n = 0;
        private Item[] objects;

    /**
     * constructor creates an empty arrayStack as API asks.
     */
        private ArrayStack() {
            objects = (Item[]) new Object[1];
        }

    /**
     * inserts item into item[0], shuffles other values along 1,
     * incrementing n and resizing the array if n == objects.length.
     * @param item represents the object to be inserted into the first value.
     */
        public void addFirst(Item item) {
            if (n == 0) {
                objects[0] = item;
                n++;
            }
            else if (n == objects.length) {
                Item[] copy = (Item[]) new Object[2 * n];
                for (int i = 0; i < n; i++) {
                    copy[i + 1] = objects[i];
                }
                copy[0] = item;
                objects = copy;
                n++;
            }
            else {
                Item[] copy = (Item[]) new Object[objects.length];
                for (int i = 0; i < n; i++) {
                    copy[i + 1] = objects[i];
                }
                copy[0] = item;
                objects = copy;
                n++;
            }
        }

    /**
     * Adds item into the objects[n] location in the array.
     * If n==objects.length resizes the array 2x current size and inserts item into location n.
     * @param item represents the object to be inserted into the last value.
     */
        public void addLast(Item item) {
            if (n == 0) {
                //if empty, creates new object array, incrementing n.
                objects = (Item[]) new Object[] { item };
                n++;
            }
            else if (n == objects.length) {
                //if full, creates copy of 2x size, adding item ad objects[n] location.
                Item[] copy = (Item[]) new Object[2 * n];
                for (int i = 0; i < n; i++) {
                    copy[i] = objects[i];
                }
                copy[n] = item;
                objects = copy;
                n++;
            }
            else {
                //if not full, just inserts item at objects[n] location, incrementing n.
                objects[n] = item;
                n++;
            }
        }

    /**
     * removes the first item at location objects[n], shuffling all items up one value in objects array.
     * @return returns the item at location objects[0]
     */
        private Item removeFirst() {
            Item item = objects[0];
            objects[0] = null;
            //if n is only quarter size of objects.length, resizes array at half objects.length,
            //copying objects[n] and shuffling each store to one less value.
            if (n > 0 && n == objects.length / 4) {
                Item[] copy = (Item[]) new Object[objects.length / 2];
                for (int i = 0; i < n; i++) {
                    copy[i] = objects[i + 1];
                }
                n--;
                objects = copy;
            }
            else {
                if (objects.length >= 2) {
                    for (int i = 0; i < n - 1; i++) {
                        objects[i] = objects[i + 1];
                    }
                }
                n--;
                objects[n] = null;
            }
            return item;
        }

    /**
     * removes and returns the last value of the item array located at objects[n-1].
     * if size of the array is quarter of the size of item array.length, resizes to half size.
     * @returns the last value in the item array.
     */
        private Item removeLast() {
            if (n == 0) {
               return null;
            }
            else {
                Item item = objects[--n];
                objects[n] = null;
                if (n > 0 && n == objects.length / 4) resize(objects.length / 2);
                return item;
            }
        }

    /**
     * resizes the item array to the size of a copy at size capacity, copying all values into the new copy array.
     * @param capacity represents the new size of the item array.length.
     */
        private void resize(int capacity) {
            Item[] copy = (Item[]) new Object[capacity];
            for (int i = 0; i < n; i++) {
                copy[i] = objects[i];
            }
            objects = copy;
        }

    /**
     * @return returns true if n = 0.
     */
        private boolean isEmpty() {
            return this.n == 0;
        }

    /**
     * calls custom ArrayIterator class with the object array store as a param.
     * @returns an iterator of all values in the array.
     */
        private Iterator<Item> iterator() {
            return new ArrayIterator(this.objects);
        }
    }
//store of the Array
    private ArrayStack arrayStack;


    // construct an empty deque
    public Deque() {
        arrayStack = new ArrayStack();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return arrayStack.isEmpty();
    }

    // return the number of items on the deque
    public int size() {
        return arrayStack.n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null");
        }
        arrayStack.addFirst(item);
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null");
        }
        arrayStack.addLast(item);
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (arrayStack.isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        else return arrayStack.removeFirst();
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (arrayStack.isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return arrayStack.removeLast();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return arrayStack.iterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        test.addFirst("b");
        test.addFirst("a");
        test.addLast("c");
        test.addLast("d");
        test.addFirst("*");
        test.removeFirst();
        StdOut.println(test.size());
        boolean empty = test.isEmpty();
        StdOut.println(empty);
        test.removeFirst();
        test.size();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        In in = new In(args[0]);
        String[] input = in.readAll().split(" ");
        StdOut.println("input : ");
        Deque<String> secondTest = new Deque<>();
        for (int i = 0; i < input.length; i++) {

            secondTest.addLast(input[i]);
            StdOut.print(input[i]+" ");
        }
        StdOut.println("output : ");
        for (int i = 0; i < input.length; i++) {
            StdOut.print(secondTest.removeFirst()+" ");
        }
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.iterator();
        deque.addLast(2);
        deque.iterator();
        deque.addFirst(3);
        deque.iterator();
        deque.removeFirst();
        deque.iterator();

    }

}
