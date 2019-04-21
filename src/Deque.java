import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 5;
    Object[] arr;
    int head;
    int tail;

    // construct an empty deque
    public Deque() {
        this.arr = new Object[INITIAL_CAPACITY];
        head = (INITIAL_CAPACITY/2);
        tail = (INITIAL_CAPACITY/2);

        System.out.println("Head = " + head);
        System.out.println("Tail = " + tail);
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == tail;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (head == 0)
            resizeArr();

        arr[head--] = item;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (tail == arr.length - 1)
            resizeArr();

        arr[tail++] = item;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item firstItem = (Item) arr[head];
        arr[head] = null;
        head++;

        return firstItem;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item lastItem = (Item) arr[tail];
        arr[tail] = null;
        tail--;
        return lastItem;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            int current = head;

            @Override
            public boolean hasNext() {
                return current < tail;
            }

            @Override
            public Item next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                return (Item) arr[++current];
            }
        };
    }

    private void resizeArr() {
        System.out.println("Resizing array");
        Object[] tempArr = new Object[2 * this.arr.length];
        int offset = (tempArr.length-size())/2;
        for (int i = offset; i < size(); i++)
            tempArr[i] = this.arr[i];
        head = offset;
        tail = offset+size();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("1");
        deque.addLast("2");
        deque.addFirst("0");
        deque.addLast("3");

        deque.removeFirst();
        deque.removeLast();
        for(String elem : deque)
            System.out.print(elem + " ");
        System.out.println();


    }
}