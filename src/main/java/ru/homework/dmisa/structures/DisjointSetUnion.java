package ru.homework.dmisa.structures;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DisjointSetUnion {

    private final Map<Integer, Integer> parents = new HashMap<>();
    private final Map<Integer, Integer> rank = new HashMap<>();

    public void makeSet(int x) {
        if (parents.containsKey(x)) {
            throw new IllegalStateException("Elements of set must be unique");
        }
        parents.put(x, x);
        rank.put(x, 0);
    }

    public int findSet(int x) {
        if (!parents.containsKey(x)) {
            throw new IllegalStateException("Set with such element does not exist: " + x);
        }
        if (x != parents.get(x)) {
            parents.put(x, findSet(parents.get(x)));
        }
        return parents.get(x);
    }

    public void union(int x, int y) {
        link(findSet(x), findSet(y));
    }

    private void link(int x, int y) {
        if (x == y) return;
        if (Objects.equals(rank.get(x), rank.get(y))) {
            rank.put(x, rank.get(x) + 1);
        }
        if (rank.get(x) < rank.get(y)) {
            parents.put(x, y);
        } else {
            parents.put(y, x);
        }
    }

}
