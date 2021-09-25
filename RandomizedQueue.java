/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // custom iterator Array Iterator class returning an iterator.
    private class ArrayIterator implements Iterator<Item> {
        // store of the array of the custom arraylist class generated beneath this class.
        private Item[] stack;
        //count for the next place in the array.
        private int i = 0;
        /**
         * constructor for storing all items in the custom ArrayList class.
         * @param items array copy with StdRandom.shuffle(items[]) called.
         */
        private ArrayIterator(Item[] items) {
            stack = items;

        }
        /**
         * shuffles through the array, looking for the next non-null item.
         * @return true if remaining items after placeCount contains a non-null, else false.
         */
        public boolean hasNext() {
            // sometimes placeCount will have an edge-case outside the array,
            // catches this and returns false if already outside the array or on edge.
            try {
                for (int j = i; j < stack.length; j++) {
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
        public void remove(){
            throw new UnsupportedOperationException ("no remove");
        }
        /**
         * checks the next value is not a null value, then checks that there is a next value before iterating placeCount,
         * if next is null, checks hasNext() for more values, then iterating placeCount is hasNext() is true and calling next() again,
         * if hasNext() returns false, and the item at placeCount is null, throws an exception.
         * @return's next item in the iterator
         */
        public Item next() {
            try {
                Item item = stack[i];
                if (item != null) {
                    if (hasNext()) {
                        i++;
                    }
                    else {
                        i++;
                    }
                }
                else {
                    if (hasNext()) {
                        i++;
                        return next();
                    }
                    else {
                        throw new NoSuchElementException("no next.");
                    }

                }
                return item;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException("no next.");
            }

        }
    }
    // generates a custom arrayList class for the given API.
    private class ArrayList {
        //number of values and a store of items.
        private Item[] objects;
        private int n = 0;

        /**
         * constructor creates an empty arrayStack as API asks.
         */
        private ArrayList() {
            objects = (Item[]) new Object[1];
        }
        /**
         * @return returns true if n = 0.
         */
        private boolean isEmpty() {
            return n == 0;
        }

        /**
         * @returns the size of the array, stored in variable n.
         */
        private int size() {
            return n;
        }

        /**
         * checks if arraylist is empty, adding item to 0th location,
         * checks if n == arrayList.legth, if true, resizes the array to 2* size, copying the values, shuffling index's along and adding item to last location n,
         * else insets item into location n,
         * increments n.
         * @param item represents item to be inserted into queue.
         */
        private void enqueue(Item item) {
            if (objects[0] == null) {
                objects[0] = item;
                n++;
            }
            else if (n == objects.length) {
                resize(2 * objects.length);
                objects[n] = item;
                n++;
            }
            else {
                objects[n] = item;
                n++;
            }

        }

        /**
         * resizes the array to 2* size, copying into new array and making objects[] = copy.
         * @param capacity represents the new size of array.
         */
        private void resize(int capacity) {
            Item[] copy = (Item[]) new Object[capacity];
            for (int i = 0; i < n; i++) {
                copy[i] = objects[i];
            }
            objects = copy;
        }

        /**
         * checks if n is the size of objects.length / 4, if true, resizes the array to half size,
         * if false, removes first item from array, shuffling all values towards arrayList[0].
         * @returns item first in queue, i.e. at location arrayList[0].
         */
        private Item dequeue() {
            int random = getRandomIndex();
            Item item = objects[random];
            if (n > 0 && n == objects.length / 4) {
                resize(objects.length / 4);
            }
            for (int i = random; i < n-1;  i++) {
                objects[i] = objects[i+1];
            }
            objects[n-1] = null;
            n--;
            return item;
        }

        /**
         * generates random index value based on array size n.
         * @returns integer index at random location.
         */
        private int getRandomIndex() {
            return StdRandom.uniform(0, n);
        }

        /**
         * creates a cloned objects array and shuffles it before calling custom iterator class.
         * @returns an iterator based on non-null objects in the custom arrayList.
         */
        private Iterator<Item> iterator() {
            Item[] shuffled = objects.clone();
            StdRandom.shuffle(shuffled);
            return new ArrayIterator(shuffled);
        }

        /**
         * gets a random index value and returns it without removing.
         * @returns random item.
         */
        private Item sample() {
            int random = getRandomIndex();
            return objects[random];
        }
    }
// store of arrayList.
    private ArrayList arrayList;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arrayList = new ArrayList();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return arrayList.size();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("enqueue(item==null)");
        }
        arrayList.enqueue(item);
    }

    // remove and return a random item
    public Item dequeue() {
        if (arrayList.isEmpty()) {
            throw new NoSuchElementException("arrayList empty");
        }
        return arrayList.dequeue();
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (arrayList.isEmpty()) {
            throw new NoSuchElementException("arrayList empty");
        }
        return arrayList.sample();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return arrayList.iterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> testOne = new RandomizedQueue<>();
        StdOut.println(testOne.isEmpty());
        testOne.enqueue("a");
        testOne.enqueue("b");
        testOne.enqueue("c");
        StdOut.println(testOne.isEmpty());
        testOne.sample();
        testOne.enqueue("d");
        StdOut.println("should be 4 -- correct is : " + testOne.size());
        StdOut.println(testOne.sample());
        String testDequeue = testOne.dequeue();
        StdOut.println(testDequeue);
        StdOut.println(testOne.dequeue());
        StdOut.println("should be 2 -- correct is : " + testOne.size());
        StdOut.println(testOne.iterator().toString());

    }

}
