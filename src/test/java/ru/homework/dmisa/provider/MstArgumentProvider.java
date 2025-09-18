package ru.homework.dmisa.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.homework.dmisa.structures.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MstArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                // Тест 1: Простой граф с 3 вершинами
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2}, {1, 2, 3}
                        }),
                        List.of(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 2}
                                })
                        )
                ),

                // Тест 2: Граф с 4 вершинами
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 4}, {0, 3, 3},
                                {1, 2, 2}, {1, 3, 5}, {2, 3, 1}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 1}, {2, 3, 1}, {1, 2, 2}
                            })
                        )
                ),

                // Тест 3: Граф с 5 вершинами
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 2}, {0, 3, 6},
                                {1, 2, 3}, {1, 3, 8}, {1, 4, 5},
                                {2, 4, 7},
                                {3, 4, 9}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 2}, {1, 2, 3}, {1, 4, 5}, {0, 3, 6}
                            })
                        )
                ),

                // Тест 4: Граф с одинаковыми весами
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 1}, {1, 2, 1},
                                {1, 3, 2}, {2, 3, 2}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 1}, {0, 2, 1}, {1, 3, 2},
                            }),
                            createExpectedMST(new int[][]{
                                    {0, 1, 1}, {0, 2, 1}, {2, 3, 2}
                            })
                        )
                ),

                // Тест 5: Граф с одной вершиной
                Arguments.of(
                        createGraph(new int[][]{}),
                        List.of(
                            createExpectedMST(new int[][]{})
                        )
                )
        );
    }

    private Map<Integer, List<Pair<Integer, Integer>>> createGraph(int[][] edges) {
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(new Pair<>(to, weight));
            graph.get(to).add(new Pair<>(from, weight));
        }

        return graph;
    }

    private Map<Integer, List<Pair<Integer, Integer>>> createExpectedMST(int[][] edges) {
        return createGraph(edges);
    }
}
