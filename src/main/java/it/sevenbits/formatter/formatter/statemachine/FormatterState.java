package it.sevenbits.formatter.formatter.statemachine;

import java.util.Objects;

public class FormatterState {

    private final String state;

    public FormatterState(final String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormatterState that = (FormatterState) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
