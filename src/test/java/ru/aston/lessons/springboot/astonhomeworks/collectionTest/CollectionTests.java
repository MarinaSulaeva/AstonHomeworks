package ru.aston.lessons.springboot.astonhomeworks.collectionTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.lessons.springboot.astonhomeworks.collection.MyCastomArrayList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionTests {

    @Test
    public void testCreateCollection() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        String [] result = {"один","два","три", null, null};
        Assertions.assertArrayEquals(result,myStringList.getArray());
    }


    @Test
    public void testAddElement() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        myStringList.add(4, "пять");
        String [] result = {"один","два","три", null, "пять"};
        Assertions.assertArrayEquals(result,myStringList.getArray());
    }

    @Test
    public void testAddAll() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        List<String> collectionForAdding=new ArrayList<>();
        collectionForAdding.add("четыре");
        collectionForAdding.add("пять");
        myStringList.addAll(collectionForAdding);
        String [] result = {"один","два","три", "четыре", "пять"};
        Assertions.assertArrayEquals(result,myStringList.getArray());
    }

    @Test
    public void testGetElement() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        String result = "два";
        Assertions.assertEquals(result, myStringList.get(1));
    }

    @Test
    public void testIsEmpty() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        Assertions.assertFalse(myStringList.isEmpty());
        Assertions.assertTrue(new MyCastomArrayList<>().isEmpty());
    }

    @Test
    public void testRemoveElement() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        String expexted = "два";
        String result = myStringList.remove(1);
        Assertions.assertEquals(expexted, result);
        String [] resultArray = {"один", "три", null, null, null};
        Assertions.assertArrayEquals(resultArray,myStringList.getArray());
    }

    @Test
    public void testRemove() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        collection.add("один");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        String expexted = "один";
        String result = myStringList.remove(1);
        Assertions.assertEquals(expexted, result);
        String [] resultArray = {"один", "три", null, null, null};
        Assertions.assertArrayEquals(resultArray,myStringList.getArray());
    }

    @Test
    public void testClear() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        collection.add("один");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        myStringList.clear();
        String [] resultArray = {null, null, null, null, null, null, null, null, null, null};
        Assertions.assertArrayEquals(resultArray,myStringList.getArray());
    }

    @Test
    public void testSort() {
        List<String> collection=new ArrayList<>();
        collection.add("один");
        collection.add("два");
        collection.add("три");
        collection.add("один");
        MyCastomArrayList<String> myStringList = new MyCastomArrayList<>(collection);
        myStringList.sort(Comparator.comparing(String::length));
        String [] resultArray = {"два", "три", "один", "один", null, null};
        Assertions.assertArrayEquals(resultArray,myStringList.getArray());
    }





}
