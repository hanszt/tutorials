package com.baeldung.algorithms;

import com.baeldung.algorithms.hillclimbing.HillClimbing;
import com.baeldung.algorithms.hillclimbing.State;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class HillClimbingAlgorithmUnitTest {
    private final Stack<String> initStack = Stream.of("B", "C", "D", "A").collect(toCollection(Stack::new));
    private final Stack<String> goalStack = Stream.of("A", "B", "C", "D").collect(toCollection(Stack::new));

    @Test
    void givenInitAndGoalState_whenGetPathWithHillClimbing_thenPathFound() {
        final var hillClimbing = new HillClimbing();

        final List<State> path = hillClimbing.getRouteWithHillClimbing(initStack, goalStack);
        assertNotNull(path);
        assertEquals(path.getLast()
                .getState()
                .getFirst(), goalStack);
    }

    @Test
    void givenCurrentState_whenFindNextState_thenBetterHeuristics() {
        final var hillClimbing = new HillClimbing();
        final List<Stack<String>> initList = new ArrayList<>(List.of(initStack));

        final var currentState = new State(initList);

        currentState.setHeuristics(hillClimbing.getHeuristicsValue(initList, goalStack));

        final var nextState = hillClimbing.findNextState(currentState, goalStack);

        assertTrue(nextState.getHeuristics() > currentState.getHeuristics());
    }
}