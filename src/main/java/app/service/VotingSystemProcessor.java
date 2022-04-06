package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import app.repository.BallotReader;
import app.repository.CandidateReader;
import app.util.validator.CollectionValidator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static app.util.console.ConsoleWriter.printOptions;

public class VotingSystemProcessor {
    public static final String CANDIDATE_FILE_PATH = "src/main/resources/candidates.txt";

    private final CandidateReader candidateReader;
    private final VoteCountingStrategyFactory voteCountingStrategyFactory;
    private final BallotReader ballotReader;

    public VotingSystemProcessor(final CandidateReader candidateReader,
                                 final VoteCountingStrategyFactory voteCountingStrategyFactory,
                                 final BallotReader ballotReader) {
        this.candidateReader = candidateReader;
        this.voteCountingStrategyFactory = voteCountingStrategyFactory;
        this.ballotReader = ballotReader;
    }

    public String processVotes(final String votingStrategy) throws Exception {
        final LinkedHashSet<Candidate> candidates = candidateReader.loadCandidateList(CANDIDATE_FILE_PATH);
        if (CollectionValidator.isNullOrEmpty(candidates)) {
            throw new ServiceException("No Candidates available.");
        }
        printOptions(candidates);
        final List<Ballot> ballots = ballotReader.loadBallots(
                candidates.stream()
                        .map(Candidate::getOption)
                        .collect(Collectors.toSet()));
        return voteCountingStrategyFactory.provideVoteCounter(votingStrategy).findTheWinner(candidates, ballots);
    }
}

