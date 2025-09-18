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
        int[] distances = new int[graph.size()];
        int[] parent = new int[graph.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // from, to, weight
        BinaryHeap<Edge> heap = new BinaryHeap<>(Comparator.comparing(Edge::weight));
        heap.add(new Edge(-1, startVertex, 0));
        Set<Edge> visited = new HashSet<>();

        while (!heap.isEmpty()) {
            var current = heap.getMinAndRemove();
            int fromParent = current.from();
            int currentVertex = current.to();
            int currentWeight = current.weight();
            if (distances[currentVertex] > currentWeight) {
                parent[currentVertex] = fromParent;
                distances[currentVertex] = currentWeight;
            }
            visited.add(current);
            for (var neighbor : graph.get(currentVertex)) {
                Edge candidate = new Edge(currentVertex, neighbor.getLeft(), neighbor.getRight());
                if (!visited.contains(candidate)) {
                    heap.add(candidate);
                }
            }
        }

        return buildGraph(parent, distances, graph.size());
    }

    private Map<Integer, List<Pair<Integer, Integer>>> buildGraph(
            int[] parent,
            int[] distances,
            int size
    ) {
        Map<Integer, List<Pair<Integer, Integer>>> resultGraph = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            if (parent[i] != -1) {
                resultGraph.putIfAbsent(parent[i], new ArrayList<>());
                resultGraph.get(parent[i]).add(new Pair<>(i, distances[i]));
            }
            resultGraph.putIfAbsent(i, new ArrayList<>());
            resultGraph.get(i).add(new Pair<>(parent[i], distances[i]));
            sum += distances[i];
        }
        System.out.println("Result sum = " + sum);
        return resultGraph;
    }
}
