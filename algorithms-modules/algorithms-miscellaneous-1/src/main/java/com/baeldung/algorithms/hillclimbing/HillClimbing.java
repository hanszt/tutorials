package com.baeldung.algorithms.hillclimbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public final class HillClimbing {

    public static void main(final String[] args) {
        final var hillClimbing = new HillClimbing();
        final var blockArr = new String[]{"B", "C", "D", "A"};
        final var startState = hillClimbing.getStackWithValues(blockArr);
        final var goalBlockArr = new String[]{"A", "B", "C", "D"};
        final var goalState = hillClimbing.getStackWithValues(goalBlockArr);
        final var solutionSequence = hillClimbing.getRouteWithHillClimbing(startState, goalState);
        solutionSequence.forEach(HillClimbing::printEachStep);
    }

    private static void printEachStep(final State state) {
        final var stackList = state.getState();
        System.out.println("----------------");
        stackList.forEach(stack -> {
            while (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
            System.out.println(" ");
        });
    }

    private Stack<String> getStackWithValues(final String[] blocks) {
        final var stack = new Stack<String>();
        for (final var block : blocks) {
            stack.push(block);
        }
        return stack;
    }

    /**
     * This method prepares path from init state to goal state
     */
    public List<State> getRouteWithHillClimbing(final Stack<String> initStateStack, final Stack<String> goalStateStack) {
        final List<Stack<String>> initStateStackList = new ArrayList<>();
        initStateStackList.add(initStateStack);
        final var initStateHeuristics = getHeuristicsValue(initStateStackList, goalStateStack);
        final var initState = new State(initStateStackList, initStateHeuristics);

        final List<State> resultPath = new ArrayList<>();
        resultPath.add(new State(initState));

        var currentState = initState;
        var noStateFound = false;
        while (!currentState.getState()
                .getFirst()
                .equals(goalStateStack) || noStateFound) {
            noStateFound = true;
            final var nextState = findNextState(currentState, goalStateStack);
            if (nextState != null) {
                noStateFound = false;
                currentState = nextState;
                resultPath.add(new State(nextState));
            }
        }

        return resultPath;
    }

    /**
     * This method finds new state from current state based on goal and
     * heuristics
     */
    public State findNextState(final State currentState, final Stack<String> goalStateStack) {
        final var listOfStacks = currentState.getState();
        final var currentStateHeuristics = currentState.getHeuristics();

        return listOfStacks.stream()
                .map(stack -> applyOperationsOnState(listOfStacks, stack, currentStateHeuristics, goalStateStack))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    /**
     * This method applies operations on the current state to get a new state
     */
    public State applyOperationsOnState(final List<Stack<String>> listOfStacks, final Stack<String> stack, final int currentStateHeuristics, final Stack<String> goalStateStack) {
        State tempState;
        final List<Stack<String>> tempStackList = new ArrayList<>(listOfStacks);
        final var block = stack.pop();
        if (stack.isEmpty()) {
            tempStackList.remove(stack);
        }
        tempState = pushElementToNewStack(tempStackList, block, currentStateHeuristics, goalStateStack);
        if (tempState == null) {
            tempState = pushElementToExistingStacks(stack, tempStackList, block, currentStateHeuristics, goalStateStack);
        }
        if (tempState == null) {
            stack.push(block);
        }
        return tempState;
    }

    /**
     * Operation to be applied on a state in order to find new states. This
     * operation pushes an element into a new stack
     */
    private State pushElementToNewStack(final List<Stack<String>> currentStackList, final String block, final int currentStateHeuristics, final Stack<String> goalStateStack) {
        State newState = null;
        final var newStack = new Stack<String>();
        newStack.push(block);

        currentStackList.add(newStack);
        final var newStateHeuristics = getHeuristicsValue(currentStackList, goalStateStack);
        if (newStateHeuristics > currentStateHeuristics) {
            newState = new State(currentStackList, newStateHeuristics);
        } else {
            currentStackList.remove(newStack);
        }
        return newState;
    }

    /**
     * Operation to be applied on a state in order to find new states. This
     * operation pushes an element into one of the other stacks to explore new
     * states
     */
    private State pushElementToExistingStacks(final Stack<String> currentStack, final List<Stack<String>> currentStackList, final String block, final int currentStateHeuristics, final Stack<String> goalStateStack) {

        final var newState = currentStackList.stream()
                .filter(stack -> stack != currentStack)
                .map(stack -> pushElementToStack(stack, block, currentStackList, currentStateHeuristics, goalStateStack))
                .filter(Objects::nonNull)
                .findFirst();

        return newState.orElse(null);
    }

    /**
     * This method pushes a block to the stack and returns new state if it's closer to goal
     */
    private State pushElementToStack(final Stack<String> stack, final String block, final List<Stack<String>> currentStackList, final int currentStateHeuristics, final Stack<String> goalStateStack) {
        stack.push(block);
        final var newStateHeuristics = getHeuristicsValue(currentStackList, goalStateStack);
        if (newStateHeuristics > currentStateHeuristics) {
            return new State(currentStackList, newStateHeuristics);
        }
        stack.pop();
        return null;
    }

    /**
     * This method returns heuristics value for given state with respect to goal
     * state
     */
    public int getHeuristicsValue(final List<Stack<String>> currentState, final Stack<String> goalStateStack) {
        return currentState.stream()
                .mapToInt(stack -> getHeuristicsValueForStack(stack, goalStateStack))
                .sum();
    }

    /**
     * This method returns heuristics value for a particular stack
     */
    public int getHeuristicsValueForStack(final Stack<String> stack, final Stack<String> goalStateStack) {
        var stackHeuristics = 0;
        var isPositioneCorrect = true;
        var goalStartIndex = 0;
        for (final var currentBlock : stack) {
            if (isPositioneCorrect && currentBlock.equals(goalStateStack.get(goalStartIndex))) {
                stackHeuristics += goalStartIndex;
            } else {
                stackHeuristics -= goalStartIndex;
                isPositioneCorrect = false;
            }
            goalStartIndex++;
        }
        return stackHeuristics;
    }

}