package app.repository;

import app.model.Ballot;

import java.util.List;
import java.util.Set;

public interface BallotReader {
    List<Ballot> loadBallots(final Set<Character> options);
}

