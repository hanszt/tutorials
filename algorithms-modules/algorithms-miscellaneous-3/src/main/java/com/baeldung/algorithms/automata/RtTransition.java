package com.baeldung.algorithms.automata;


/**
 * Transition in finite state machine.
 */
public final class RtTransition implements Transition {

    private final String rule;
    private final State next;
    
    /**
     * Ctor.
     * @param rule Rule that a character has to meet 
     *  in order to get to the next state.  
     * @param next Next state.
     */
    public RtTransition (final String rule, final State next) {
        this.rule = rule;
        this.next = next;
    }
    
    public State state() {
        return this.next;
    }

    public boolean isPossible(final CharSequence c) {
        return this.rule.equalsIgnoreCase(String.valueOf(c));
    }

}
