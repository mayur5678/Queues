import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 8;
    Object[] arr;
    int head;
    int tail;

    // construct an empty deque
    public Deque() {
        this.arr = new Object[INITIAL_CAPACITY];
        head = INITIAL_CAPACITY/2 - 1;
        tail = INITIAL_CAPACITY/2;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (tail - head) == 1;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head - 1;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (head == 0)
            resizeArr(2 * arr.length);

        arr[head--] = item;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (tail == arr.length - 1)
            resizeArr(2 * arr.length);

        arr[tail++] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        int size = size();
        if (size>0 && size <= arr.length / 4 && arr.length/2 >= INITIAL_CAPACITY)
            resizeArr(arr.length/2);

        Item firstItem = (Item) arr[head + 1];
        arr[++head] = null;

        return firstItem;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        int size = size();
        if (size>0 && size <= arr.length / 4 && arr.length/2 >= INITIAL_CAPACITY)
            resizeArr(arr.length/2);

        Item lastItem = (Item) arr[tail - 1];
        arr[--tail] = null;

        return lastItem;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            int current = head;

            @Override
            public boolean hasNext() {
                return current < tail - 1;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return (Item) arr[++current];
            }
        };
    }

    private void resizeArr(int size) {
        Object[] tempArr = new Object[size];

        int oldSize=size();
        int oldHead=head;
        int oldTail=tail;

        head = tempArr.length / 4 ; //Needs to be changed. Always closer to head
        tail = head + oldSize + 1;

        for (int i = oldHead, j=0; i <= oldTail; i++,j++) {
            tempArr[j + head] = this.arr[i];
        }

        this.arr = tempArr;
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("0");
        deque.addLast("1");
        deque.addFirst("2");
        deque.addLast("3");
        deque.addFirst("4");
        deque.addLast("5");
        deque.addFirst("6");
        deque.addLast("7");
        deque.addFirst("8");
        deque.addLast("9");


        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();

        for (String elem : deque)
            System.out.print(elem + " ");
        System.out.println();


    }
}