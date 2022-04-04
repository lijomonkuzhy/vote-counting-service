package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import app.repository.CandidateFileReader;
import app.repository.BallotReader;
import app.util.validator.CollectionValidator;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static app.util.console.ConsoleWriter.printOptions;

public class VoteCounter {
    public static final String CANDIDATE_FILE_PATH = "src/main/resources/candidates.txt";

    private final CandidateFileReader candidateFileReader;
    private final PreferentialVoteCounter preferentialVoteCounter;
    private final BallotReader ballotReader;

    public VoteCounter(final CandidateFileReader candidateFileReader,
                       final PreferentialVoteCounter preferentialVoteCounter,
                       final BallotReader ballotReader) {
        this.candidateFileReader = candidateFileReader;
        this.preferentialVoteCounter = preferentialVoteCounter;
        this.ballotReader = ballotReader;
    }

    public String processVotes() throws IOException, ServiceException {
        final LinkedHashSet<Candidate> candidates = candidateFileReader.loadCandidateList(CANDIDATE_FILE_PATH);
        if (CollectionValidator.isNullOrEmpty(candidates)) {
            throw new ServiceException("No Candidates available.");
        }
        printOptions(candidates);
        final List<Ballot> ballots = ballotReader.loadBallots(
                candidates.stream()
                .map(Candidate::getOption)
                .collect(Collectors.toSet()));
        return preferentialVoteCounter.findTheWinner(candidates, ballots);
    }
}

