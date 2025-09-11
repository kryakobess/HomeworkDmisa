package ru.homework.dmisa.structures;

public record Pair<L, R> (
        L left,
        R right
) {

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
