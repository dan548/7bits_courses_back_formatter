package it.sevenbits.formatter.formatter.statemachine;

public final class FormatterStateTransitions {

    private final FormatterStateMap map;

    public FormatterStateTransitions() {
        map = new FormatterStateMap();
    }

    public FormatterState getStartState() {
        return map.getStartState();
    }

    public FormatterState nextState(final FormatterState state, final String tokenName) {
        return map.getNextState(state, tokenName);
    }
}
