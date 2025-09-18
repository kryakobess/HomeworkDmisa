package ru.homework.dmisa.structures;

public record Edge(int from, int to, int weight) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;

        return (to == edge.to && from == edge.from && weight == edge.weight)
                || (to == edge.from && from == edge.to && edge.weight == weight);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(weight) ^ Integer.hashCode(Math.min(from, to)) ^ Integer.hashCode(Math.max(from, to));
    }
}
