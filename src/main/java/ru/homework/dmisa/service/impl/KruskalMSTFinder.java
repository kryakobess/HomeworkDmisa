package ru.homework.dmisa.service.impl;

import ru.homework.dmisa.service.MSTFinder;
import ru.homework.dmisa.structures.DisjointSetUnion;
import ru.homework.dmisa.structures.Edge;
import ru.homework.dmisa.structures.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class KruskalMSTFinder implements MSTFinder {

    @Override
    public Map<Integer, List<Pair<Integer, Integer>>> findMst(Map<Integer, List<Pair<Integer, Integer>>> graph) {
        if (graph.isEmpty()) return new HashMap<>();

        List<Edge> sortedEdges = getWeightedEdges(graph);
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion();
        for (int vertex : graph.keySet()) {
            disjointSetUnion.makeSet(vertex);
        }
        List<Edge> resultEdges = new ArrayList<>();

        for (Edge edge : sortedEdges) {
            if (disjointSetUnion.findSet(edge.from()) != disjointSetUnion.findSet(edge.to())) {
                disjointSetUnion.union(edge.from(), edge.to());
                resultEdges.add(edge);
            }
        }

        return buildGraph(resultEdges);
    }

    private List<Edge> getWeightedEdges(Map<Integer, List<Pair<Integer, Integer>>> graph) {
        return graph.entrySet().stream()
                .flatMap(entry -> {
                    var from = entry.getKey();
                    return entry.getValue()
                            .stream()
                            .map(edge -> new Edge(from, edge.getLeft(), edge.getRight()));
                })
                .sorted(Comparator.comparing(Edge::weight))
                .collect(Collectors.toList());
    }

    private Map<Integer, List<Pair<Integer, Integer>>> buildGraph(List<Edge> edges) {
        Map<Integer, List<Pair<Integer, Integer>>> result = new HashMap<>();
        int sum = 0;
        for (var edge : edges) {
            result.putIfAbsent(edge.from(), new ArrayList<>());
            result.putIfAbsent(edge.to(), new ArrayList<>());

            sum += edge.weight();

            result.get(edge.from()).add(new Pair<>(edge.to(), edge.weight()));
            result.get(edge.to()).add(new Pair<>(edge.from(), edge.weight()));
        }

        System.out.println("Result sum = " + sum);
        return result;
    }

}
