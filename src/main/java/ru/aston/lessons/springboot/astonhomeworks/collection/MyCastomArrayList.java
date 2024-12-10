package ru.aston.lessons.springboot.astonhomeworks.collection;

import lombok.Getter;

import javax.print.DocFlavor;
import java.util.*;

public class MyCastomArrayList<E> extends AbstractList<E> {
    private static final int INIT_CAPACITY = 10;
    private static final double EXPANSION_RATIO = 1.5;
    @Getter
    private E[] array;
    private int size;


    public MyCastomArrayList() {
        this.array = (E[]) new Object[INIT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public MyCastomArrayList(int capacity) {
        this.array = (E[]) new Object[capacity];
        this.size = 0;
    }

    public MyCastomArrayList(Collection<? extends E> c) {
        E[] collectionInArray = (E[]) c.toArray();
        int sizeNewArray = (int) Math.round(collectionInArray.length * EXPANSION_RATIO);
        this.array = (E[]) new Object[sizeNewArray];
        System.arraycopy(collectionInArray, 0, this.array, 0, collectionInArray.length);
        this.size = collectionInArray.length;
    }

    public void add(int index, E element) {
        if (index > this.array.length) {
            E[] temp = this.array;
            int sizeNewArray = (int) Math.round(index * EXPANSION_RATIO);
            this.array = (E[]) new Object[sizeNewArray];
            System.arraycopy(temp, 0, this.array, 0, temp.length);
            this.array[index] = element;
        } else {
            for (int i = size - 1; i > index; i--) {
                this.array[i] = this.array[i - 1];
            }
            this.array[index] = element;
        }
        this.size = this.size + 1;
    }

    public boolean addAll(Collection<? extends E> c) {
        int sizeCollection = c.size();
        E[] collectionForAdd = (E[]) c.toArray();
        if (collectionForAdd.getClass() != this.array.getClass()) {
            throw new ClassCastException();
        }
        if ((this.size + sizeCollection) <= this.array.length) {
            System.arraycopy(collectionForAdd, 0, this.array, this.size, sizeCollection);
        } else {
            E[] temp = this.array;
            int sizeNewArray = (int) Math.round((sizeCollection + this.size) * EXPANSION_RATIO);
            this.array = (E[]) new Object[sizeNewArray];
            System.arraycopy(temp, 0, this.array, 0, temp.length);
            System.arraycopy(collectionForAdd, 0, this.array, this.size, sizeCollection);

        }
        this.size = this.size + sizeCollection;

        return true;
    }

    public E get(int index) {
        if (index > this.array.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[index];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E remove(int index) {
        if (index >= this.array.length) {
            throw new IndexOutOfBoundsException();
        }
        E removeElement = this.array[index];
        for (int i = index; i < this.array.length - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.array[this.array.length - 1] = null;
        this.size = this.size - 1;
        return removeElement;
    }

    public boolean remove(Object o) {
        int countOfObject = 0;
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == o) {
                countOfObject++;
                for (int j = i; j < this.array.length - 1; j++) {
                    this.array[j] = this.array[j + 1];
                }
            }
        }
        this.size = this.size - countOfObject;
        return countOfObject != 0;
    }

    public void clear() {
        this.array = (E[]) new Object[INIT_CAPACITY];
        this.size = 0;
    }

    public void sort(Comparator<? super E> c) {
        Arrays.sort(this.array, 0, size, c);
        Map<String, String> map = new HashMap<>();
    }


    @Override
    public String toString() {
        return "MyCastomArrayList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

}