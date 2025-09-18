package ru.homework.dmisa.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.homework.dmisa.structures.Pair;

import java.util.*;
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
                ),

                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2}, {0, 3, 3},
                                {1, 2, 4}, {1, 3, 5},
                                {2, 3, 6}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 1}, {0, 2, 2}, {0, 3, 3}
                            })
                        )
                ),

// Тест 7: Граф с циклом и тяжелыми ребрами
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 10},
                                {1, 2, 2}, {1, 3, 15},
                                {2, 3, 3}, {2, 4, 20},
                                {3, 4, 4}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 1}, {1, 2, 2}, {2, 3, 3}, {3, 4, 4}
                            })
                        )
                ),

// Тест 8: Граф с изолированными вершинами (должны быть включены в MST)
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 5}, {2, 3, 3}, {4, 5, 2}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 5}, {2, 3, 3}, {4, 5, 2}
                            })
                        )
                ),

// Тест 9: Большой вес на критическом ребре
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 100}, {0, 2, 1}, {1, 2, 1},
                                {1, 3, 1}, {2, 3, 100}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 2, 1}, {1, 2, 1}, {1, 3, 1}
                            })
                        )
                ),

// Тест 11: Граф с вершинами, не имеющими индексов 0,1,2...
                Arguments.of(
                        createGraph(new int[][]{
                                {10, 20, 5}, {10, 30, 3}, {20, 30, 4},
                                {20, 40, 2}, {30, 40, 6}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {10, 30, 3}, {20, 40, 2}, {20, 30, 4}
                            })
                        )
                ),

// Тест 12: Граф где MST не включает минимальное ребро из вершины
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 10}, {0, 2, 20},
                                {1, 2, 5}, {1, 3, 15},
                                {2, 3, 30}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 10}, {1, 2, 5}, {1, 3, 15}
                            })
                        )
                ),

// Тест 13: Граф с несколькими возможными MST (одинаковый вес)
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 1},
                                {1, 2, 1}, {1, 3, 1},
                                {2, 3, 1}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 1}, {1, 3, 1}
                                }),
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {1, 2, 1}, {2, 3, 1}
                                }),
                                createExpectedMST(new int[][]{
                                        {0, 2, 1}, {1, 2, 1}, {1, 3, 1}
                                }),
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 1}, {2, 3, 1}
                                })
                        )
                ),

// Тест 14: Граф с одним ребром
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 7}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 7}
                            })
                        )
                ),

// Тест 15: Полностью связный граф с возрастающими весами
                Arguments.of(
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2}, {0, 3, 3}, {0, 4, 4},
                                {1, 2, 5}, {1, 3, 6}, {1, 4, 7},
                                {2, 3, 8}, {2, 4, 9},
                                {3, 4, 10}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 1}, {0, 2, 2}, {0, 3, 3}, {0, 4, 4}
                            })
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
