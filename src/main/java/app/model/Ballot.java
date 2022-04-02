package app.model;

import java.util.List;
import java.util.Objects;

public class Ballot {
    private final List<Character> preferences;

    private Ballot(Builder builder) {
        preferences = builder.preferences;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Ballot copy) {
        Builder builder = new Builder();
        builder.preferences = copy.getPreferences();
        return builder;
    }

    public List<Character> getPreferences() {
        return preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ballot ballot = (Ballot) o;
        return Objects.equals(preferences, ballot.preferences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferences);
    }

    @Override
    public String toString() {
        return "Ballot{" +
                "preferences=" + preferences +
                '}';
    }


    public static final class Builder {
        private List<Character> preferences;

        private Builder() {
        }

        public Builder withPreferences(List<Character> val) {
            preferences = val;
            return this;
        }

        public Ballot build() {
            return new Ballot(this);
        }
    }
}
