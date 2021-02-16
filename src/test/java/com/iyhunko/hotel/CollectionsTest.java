package com.iyhunko.hotel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsTest {

    @Test
    void shouldCheckMap() {
        // Like associative arrays
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key1", 2);
        map.put("key2", 2);
        map.put("key3", 2);
        map.put("key3", 6);
        System.out.println(map.get("key3"));

        Assertions.assertEquals(map.get("key3"), 6);

        Assertions.assertTrue(map.containsKey("key2"));
    }

    @Test
    void shouldCheckArrayList() {
        // <Integer> - is a generic
        List<String> list = new ArrayList();

        list.add("one");
        list.add("two");

        Assertions.assertEquals(list.get(0), "one");
    }

    @Test
    void shouldCheckLinkedList() {
        // Items are linked in 2 directions(next/prev)
        List<String> list = new ArrayList();

        list.add("one");
        list.add("two");

        Assertions.assertEquals(list.get(0), "one");
    }

    @Test
    void checkCheckSet() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        // A set of strings with random order
        Set<String> strings = map.keySet();

        Assertions.assertEquals(2, map.get("key2"));
    }

    @Test
    void shouldCheckStream() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");

        Set<String> set = list.stream().collect(Collectors.toSet());

        Assertions.assertTrue(set.contains(list.get(0)));
    }
}
