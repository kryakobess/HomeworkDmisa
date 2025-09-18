package ru.homework.dmisa;


import ru.homework.dmisa.service.MSTFinder;
import ru.homework.dmisa.service.impl.KruskalMSTFinder;
import ru.homework.dmisa.service.impl.PrimMSTFinder;
import ru.homework.dmisa.structures.BinaryHeap;
import ru.homework.dmisa.structures.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        graph.put(0, List.of(new Pair<>(1, 9), new Pair<>(2, 75)));
        graph.put(1, List.of(new Pair<>(0, 9), new Pair<>(2, 95), new Pair<>(3, 19), new Pair<>(4, 42)));
        graph.put(2, List.of(new Pair<>(0, 75), new Pair<>(1, 95), new Pair<>(3, 51)));
        graph.put(3, List.of(new Pair<>(1, 19), new Pair<>(2, 51), new Pair<>(4, 31)));
        graph.put(4, List.of(new Pair<>(1, 42), new Pair<>(3, 31)));

        MSTFinder finder = new KruskalMSTFinder();

        System.out.println(finder.findMst(graph));
    }
}