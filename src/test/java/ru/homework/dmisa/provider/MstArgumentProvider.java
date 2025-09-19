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
                Arguments.of(
                        "Тест 1: Простой граф с 3 вершинами",
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2}, {1, 2, 3}
                        }),
                        List.of(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 2}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 2: Граф с 4 вершинами",
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

                Arguments.of(
                        "Тест 3: Граф с 5 вершинами",
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

                Arguments.of(
                        "Тест 4: Граф с одинаковыми весами",
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

                Arguments.of(
                        "Тест 5: Граф с одной вершиной",
                        createGraph(new int[][]{}),
                        List.of(
                            createExpectedMST(new int[][]{})
                        )
                ),

                Arguments.of(
                        "Тест 6",
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

                Arguments.of(
                        "Тест 7: Граф с циклом и тяжелыми ребрами",
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

                Arguments.of(
                        "Тест 8: Большой вес на критическом ребре",
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

                Arguments.of(
                        "Тест 10: Граф с вершинами, не имеющими индексов 0,1,2...",
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

                Arguments.of(
                        "Тест 11: Граф где MST не включает минимальное ребро из вершины",
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

                Arguments.of(
                        "Тест 12: Граф с несколькими возможными MST (одинаковый вес)",
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

                Arguments.of(
                        "Тест 13: Граф с одним ребром",
                        createGraph(new int[][]{
                                {0, 1, 7}
                        }),
                        List.of(
                            createExpectedMST(new int[][]{
                                    {0, 1, 7}
                            })
                        )
                ),

                Arguments.of(
                        "Тест 14: Полностью связный граф с возрастающими весами",
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
                ),

                Arguments.of(
                        "Тест 15: Граф с вершинами, имеющими пропуски в нумерации",
                        createGraph(new int[][]{
                                {1, 3, 5}, {1, 5, 3}, {3, 5, 4},
                                {3, 7, 2}, {5, 7, 6}, {5, 9, 1},
                                {7, 9, 7}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {5, 9, 1}, {3, 7, 2}, {1, 5, 3},
                                        {3, 5, 4}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 16: Граф с \"звездной\" структурой",
                        createGraph(new int[][]{
                                {0, 1, 10}, {0, 2, 20}, {0, 3, 30}, {0, 4, 40},
                                {1, 2, 50}, {1, 3, 60}, {1, 4, 70},
                                {2, 3, 80}, {2, 4, 90},
                                {3, 4, 100}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 10}, {0, 2, 20}, {0, 3, 30}, {0, 4, 40}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 17: Граф с \"мостом\" между двумя компонентами",
                        createGraph(new int[][]{
                                {0, 1, 2}, {1, 2, 3}, {2, 0, 4},  // Первый треугольник
                                {3, 4, 1}, {4, 5, 2}, {5, 3, 3},   // Второй треугольник
                                {2, 3, 10}                         // Мост
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 2}, {1, 2, 3},
                                        {3, 4, 1}, {4, 5, 2},
                                        {2, 3, 10}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 18: Граф где MST требует пропустить локально минимальное ребро",
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 100},
                                {1, 2, 2}, {1, 3, 100},
                                {2, 3, 1}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {2, 3, 1}, {1, 2, 2}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 19: Граф с весами-степенями двойки",
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2}, {0, 3, 4},
                                {1, 2, 8}, {1, 3, 16},
                                {2, 3, 32}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 2}, {0, 3, 4}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 20: Граф с \"обходным путем\"",
                        createGraph(new int[][]{
                                {0, 1, 10}, {0, 2, 15},
                                {1, 2, 5}, {1, 3, 20},
                                {2, 3, 10}, {2, 4, 25},
                                {3, 4, 5}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {1, 2, 5}, {3, 4, 5}, {2, 3, 10}, {0, 1, 10}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 21: Граф с \"тяжелым\" центром",
                        createGraph(new int[][]{
                                {0, 2, 1}, {1, 2, 1}, {3, 2, 1}, {4, 2, 1},  // Легкие ребра к центру
                                {0, 1, 10}, {0, 3, 10}, {0, 4, 10},          // Тяжелые прямые ребра
                                {1, 3, 10}, {1, 4, 10},
                                {3, 4, 10}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 2, 1}, {1, 2, 1}, {3, 2, 1}, {4, 2, 1}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 22: Граф с \"альтернативными маршрутами\"",
                        createGraph(new int[][]{
                                {0, 1, 5}, {0, 2, 6},
                                {1, 3, 7}, {2, 3, 8},
                                {1, 4, 3}, {3, 4, 4}, {2, 4, 9}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 5}, {1, 4, 3}, {3, 4, 4}, {1, 3, 7}
                                }),
                                createExpectedMST(new int[][]{
                                        {0, 1, 5}, {1, 4, 3}, {3, 4, 4}, {0, 2, 6}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 23: Граф с \"цепочкой\" вершин",
                        createGraph(new int[][]{
                                {0, 1, 2}, {1, 2, 3}, {2, 3, 4}, {3, 4, 5},
                                {0, 2, 10}, {0, 3, 20}, {0, 4, 30},
                                {1, 3, 40}, {1, 4, 50},
                                {2, 4, 60}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 2}, {1, 2, 3}, {2, 3, 4}, {3, 4, 5}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 24: Граф с \"ромбовидной\" структурой",
                        createGraph(new int[][]{
                                {0, 1, 3}, {0, 2, 4},
                                {1, 3, 5}, {2, 3, 6},
                                {1, 2, 2}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {1, 2, 2}, {0, 1, 3}, {1, 3, 5}
                                }),
                                createExpectedMST(new int[][]{
                                        {1, 2, 2}, {0, 1, 3}, {0, 2, 4}
                                }),
                                createExpectedMST(new int[][]{
                                        {1, 2, 2}, {0, 2, 4}, {2, 3, 6}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 25: Граф с \"деревом\" и \"обратными\" ребрами",
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2},
                                {1, 3, 3}, {1, 4, 4},
                                {2, 5, 5}, {2, 6, 6},
                                {3, 4, 7}, {5, 6, 8},  // Обратные ребра внутри поддеревьев
                                {1, 2, 9}, {3, 5, 10}  // Обратные ребра между поддеревьями
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 2},
                                        {1, 3, 3}, {1, 4, 4},
                                        {2, 5, 5}, {2, 6, 6}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 26: Граф с \"тяжелым\" кликом",
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 1}, {0, 3, 1},  // Легкие ребра из центра
                                {1, 2, 100}, {1, 3, 100}, {2, 3, 100}  // Тяжелый клик
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 1}, {0, 3, 1}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 27: Граф с \"веерной\" структурой",
                        createGraph(new int[][]{
                                {0, 1, 1}, {0, 2, 2}, {0, 3, 3}, {0, 4, 4}, {0, 5, 5},
                                {1, 2, 10}, {1, 3, 20}, {1, 4, 30}, {1, 5, 40},
                                {2, 3, 50}, {2, 4, 60}, {2, 5, 70},
                                {3, 4, 80}, {3, 5, 90},
                                {4, 5, 100}
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {0, 1, 1}, {0, 2, 2}, {0, 3, 3}, {0, 4, 4}, {0, 5, 5}
                                })
                        )
                ),

                Arguments.of(
                        "Тест 28: Граф с \"пересекающимися\" циклами",
                        createGraph(new int[][]{
                                {0, 1, 3}, {1, 2, 4}, {2, 0, 5},  // Цикл 1
                                {2, 3, 2}, {3, 4, 1}, {4, 2, 6},   // Цикл 2
                                {0, 4, 7}, {1, 3, 8}               // Пересекающие ребра
                        }),
                        Arrays.asList(
                                createExpectedMST(new int[][]{
                                        {3, 4, 1}, {2, 3, 2}, {0, 1, 3}, {1, 2, 4}
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
