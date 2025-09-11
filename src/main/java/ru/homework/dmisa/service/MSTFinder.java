package ru.homework.dmisa.service;

import ru.homework.dmisa.structures.Pair;

import java.util.List;
import java.util.Map;

public interface MSTFinder {

    Map<Integer, List<Pair<Integer, Integer>>> findMst(Map<Integer, List<Pair<Integer, Integer>>> graph);
}
