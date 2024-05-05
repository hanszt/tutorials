package com.baeldung.algorithms.hillclimbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.util.stream.Collectors.toCollection;

public final class State {
    private final List<Stack<String>> state;
    private int heuristics;

    public State(final List<Stack<String>> state) {
        this.state = state;
    }

    State(final List<Stack<String>> state, final int heuristics) {
        this.state = state;
        this.heuristics = heuristics;
    }

    State(final State state) {
        this.state = new ArrayList<>();
        for (final var s : state.getState()) {
            this.state.add(s.stream().collect(toCollection(Stack::new)));
        }
        this.heuristics = state.getHeuristics();
    }

    public List<Stack<String>> getState() {
        return state;
    }

    public int getHeuristics() {
        return heuristics;
    }

    public void setHeuristics(final int heuristics) {
        this.heuristics = heuristics;
    }
}