import org.omg.CORBA.Object;

import java.util.Arrays;

public class ArrayList implements List {
    private static final int NOT_FOUND = -1;
    private Object[] array;
    private int size = 0;

    private ArrayList(Object[] array) {
        this.array = array;
    }

    public ArrayList() {
        array = new Object[10];
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object item) {
        growAsNeeded();
        array[size++] = item;
    }

    private void growAsNeeded() {
        if (array.length == size) {
           /* Object[] newArray = new Object[(array.length * 3 / 2) + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray; */
            array = Arrays.copyOf(array, (array.length * 3 / 2) + 1);
        }
    }

    @Override
    public void add(int index, Object item) {
        checkForRange(index);
        growAsNeeded();
        shiftItems(index);
        array[index] = item;
        size++;
    }

    private void checkForRange(int index) {
        if ((index > size) || (index < 0))
            throw new IndexOutOfBoundsException();
    }

    private void shiftItems(int index) {
        for (int i = size; i > index; --i) {
            array[i] = array[i - 1];
        }
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int lastIndexOf(Object obj) {

        for (int i = size - 1; i >= 0; i--) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public void set(int index, Object item) {
        checkForRange(index);
        array[index] = item;
    }

    @Override
    public void remove(int index) {
        checkForRange(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index != NOT_FOUND) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public List subList(int from, int to) {
        List list = new ArrayList();
        for (int i = from; i <= to - 1; i++) {
            list.add(array[i]);
        }
        return list;

//        Object[] subArray = new Object[to - from + 1];
//        System.arraycopy(array, from, subArray, 0, to - from + 1);
//        return new ArrayList(subArray);
    }
}
