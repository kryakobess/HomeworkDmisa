package ru.homework.dmisa;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.homework.dmisa.provider.MstArgumentProvider;
import ru.homework.dmisa.service.impl.KruskalMSTFinder;
import ru.homework.dmisa.service.impl.PrimMSTFinder;
import ru.homework.dmisa.structures.Edge;
import ru.homework.dmisa.structures.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MstTest {

    @ParameterizedTest
    @ArgumentsSource(MstArgumentProvider.class)
    void testKruskal(
            String testName,
            Map<Integer, List<Pair<Integer, Integer>>> graph,
            List<Map<Integer, List<Pair<Integer, Integer>>>> expectedMST
    ) {
        System.out.println(testName);
        KruskalMSTFinder kruskalMSTFinder = new KruskalMSTFinder();

        var res = kruskalMSTFinder.findMst(graph);

        assertNotNull(res);
        assertEqualsMst(expectedMST, res);
    }

    @ParameterizedTest
    @ArgumentsSource(MstArgumentProvider.class)
    void testPrim(
            String testName,
            Map<Integer, List<Pair<Integer, Integer>>> graph,
            List<Map<Integer, List<Pair<Integer, Integer>>>> expectedMST
    ) {
        System.out.println(testName);
        PrimMSTFinder kruskalMSTFinder = new PrimMSTFinder();

        var res = kruskalMSTFinder.findMst(graph);

        assertNotNull(res);
        assertEqualsMst(expectedMST, res);
    }

    private void assertEqualsMst(
            List<Map<Integer, List<Pair<Integer, Integer>>>> expectedCandidates,
            Map<Integer, List<Pair<Integer, Integer>>> actual
    ) {
        assertThat(expectedCandidates).anyMatch(expected -> {
            System.out.println("Actual: " + getEdges(actual) + "\n");
            return getEdges(expected).equals(getEdges(actual));
        });
    }

    private List<Edge> getEdges(Map<Integer, List<Pair<Integer, Integer>>> graph) {
        return graph.entrySet().stream()
                .flatMap(entry -> {
                    var from = entry.getKey();
                    return entry.getValue()
                            .stream()
                            .map(edge -> new Edge(from, edge.getLeft(), edge.getRight()));
                })
                .distinct()
                .sorted(Comparator.comparing(Edge::weight))
                .collect(Collectors.toList());
    }

}