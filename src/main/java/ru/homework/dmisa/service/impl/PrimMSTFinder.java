package ru.homework.dmisa.service.impl;

import ru.homework.dmisa.service.MSTFinder;
import ru.homework.dmisa.structures.BinaryHeap;
import ru.homework.dmisa.structures.Edge;
import ru.homework.dmisa.structures.Pair;

import java.util.*;

public class PrimMSTFinder implements MSTFinder {

    @Override
    public Map<Integer, List<Pair<Integer, Integer>>> findMst(Map<Integer, List<Pair<Integer, Integer>>> graph) {
        if (graph.isEmpty()) return new HashMap<>();
        Integer startVertex = graph.keySet().iterator().next();
        return findMst(graph, startVertex);
    }

    private Map<Integer, List<Pair<Integer, Integer>>> findMst(
            Map<Integer, List<Pair<Integer, Integer>>> graph,
            Integer startVertex
    ) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> parents = new HashMap<>();
        BinaryHeap<Edge> heap = new BinaryHeap<>(Comparator.comparing(Edge::weight));
        for (int vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            parents.put(vertex, -1);
        }

        heap.add(new Edge(-1, startVertex, 0));
        Set<Integer> visited = new HashSet<>();

        while (!heap.isEmpty()) {
            var current = heap.getMinAndRemove();
            int fromParent = current.from();
            int currentVertex = current.to();
            int currentWeight = current.weight();

            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);

            if (distances.get(currentVertex) > currentWeight) {
                parents.put(currentVertex, fromParent);
                distances.put(currentVertex, currentWeight);
            }

            for (var neighbor : graph.get(currentVertex)) {
                Edge candidate = new Edge(currentVertex, neighbor.getLeft(), neighbor.getRight());
                heap.add(candidate);
            }
        }

        return buildGraph(parents, distances);
    }

    private Map<Integer, List<Pair<Integer, Integer>>> buildGraph(
            Map<Integer, Integer> parent,
            Map<Integer, Integer> distances
    ) {
        Map<Integer, List<Pair<Integer, Integer>>> resultGraph = new HashMap<>();
        int sum = 0;
        for (var i : parent.keySet()) {
            if (parent.get(i) != -1) {
                resultGraph.putIfAbsent(parent.get(i), new ArrayList<>());
                resultGraph.get(parent.get(i)).add(new Pair<>(i, distances.get(i)));

                resultGraph.putIfAbsent(i, new ArrayList<>());
                resultGraph.get(i).add(new Pair<>(parent.get(i), distances.get(i)));
                sum += distances.get(i);
            }
        }
        System.out.println("Result sum = " + sum);
        return resultGraph;
    }
}
