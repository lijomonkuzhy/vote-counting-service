package app.model;

import java.util.Objects;

public class Candidate {
    private final char option;
    private final String name;

    public char getOption() {
        return option;
    }

    private Candidate(Builder builder) {
        option = builder.option;
        name = builder.name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return option == candidate.option && name.equals(candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, option);
    }

    @Override
    public String toString() {
        return String.format("%s. %s", option, name);
    }

    public static final class Builder {
        private char option;
        private String name;

        private Builder() {
        }

        public Builder withOption(char val) {
            option = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Candidate build() {
            return new Candidate(this);
        }
    }
}
