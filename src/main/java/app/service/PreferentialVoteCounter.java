package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static app.util.console.ConsoleWriter.printCandidateBallotCount;
import static app.util.console.ConsoleWriter.printInfo;

public class PreferentialVoteCounter implements VoteCounter {

    @Override
    public String findTheWinner(final Set<Candidate> candidates, final List<Ballot> ballots) throws ServiceException {
        printInfo("Vote counting in progress...");

        Map<Character, List<Ballot>> candidateToBallotsMap = buildCandidateToBallotMap(ballots);

        char candidateWithMostBallots;
        char candidateWithLeastBallots;
        int roundCount = 0;
        int quotaRequiredToWin;

        do {
            roundCount++;
            printInfo(String.format("****** Round %s ******", roundCount));
            quotaRequiredToWin = quotaRequiredToWin(candidateToBallotsMap);
            printCandidateBallotCount(candidateToBallotsMap);
            candidateWithMostBallots = findCandidateWithMostBallots(candidateToBallotsMap);
            candidateWithLeastBallots = findCandidateWithLeastBallots(candidateToBallotsMap);

            if (hasAllremainingCandidatesHaveSameBallotCount(candidateWithMostBallots, candidateWithLeastBallots)) {
                break;
            }

            if ((candidateToBallotsMap.get(candidateWithMostBallots).size()) < quotaRequiredToWin) {
                allocateBallotsToNextPreference(candidateToBallotsMap, candidateWithLeastBallots);
                candidateToBallotsMap.remove(candidateWithLeastBallots);
                printInfo(String.format("Candidate with least number of ballots will be eliminated and the votes will be reallocated.The candidate selected in this round is '%s'", candidateWithLeastBallots));
            }

        } while ((candidateToBallotsMap.get(candidateWithMostBallots).size()) < quotaRequiredToWin);

        return findTheCandidateWithWinningOption(candidateWithMostBallots, candidates);
    }

    private boolean hasAllremainingCandidatesHaveSameBallotCount(final char candidateWithMostBallots,
                                                                 final char candidateWithLeastBallots) {
        return candidateWithMostBallots == candidateWithLeastBallots;
    }

    private int quotaRequiredToWin(final Map<Character, List<Ballot>> candidateToBallotsMap) {
        int quotaRequiredToWin = findBallotCount(candidateToBallotsMap) / 2 + 1;
        printInfo(String.format("Quota required to win is %s", quotaRequiredToWin));
        return quotaRequiredToWin;
    }

    private String findTheCandidateWithWinningOption(final char candidateOption, final Set<Candidate> candidates) throws ServiceException {
        return candidates.stream().filter(candidate -> candidate.getOption() == candidateOption).map(Candidate::toString).findFirst()
                .orElseThrow(() -> new ServiceException("Failed to find the winner. Please try again."));
    }

    private Map<Character, List<Ballot>> buildCandidateToBallotMap(List<Ballot> ballots) {
        return ballots.stream()
                .collect(Collectors.groupingBy(ballot -> ballot.getPreferences().get(0)));
    }

    private static void allocateBallotsToNextPreference(Map<Character, List<Ballot>> candidateToBallotsMap, char candidate) {
        List<Ballot> candidateBallots = candidateToBallotsMap.get(candidate);

        for (Ballot ballot : candidateBallots) {
            candidateToBallotsMap.remove(ballot.getPreferences().get(0));

            for (char preference : ballot.getPreferences()) {
                if (candidateToBallotsMap.containsKey(preference)) {
                    candidateToBallotsMap.get(preference).add(ballot);
                    break;
                }
            }
        }
    }

    private static Character findCandidateWithMostBallots(Map<Character, List<Ballot>> candidateToBallotsMap) {

        Character candidate = candidateToBallotsMap.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(null);

        printInfo(String.format("Leading Candidate selected is '%s'", candidate));
        return candidate;
    }

    private static Character findCandidateWithLeastBallots(Map<Character, List<Ballot>> candidateToBallotsMap) {

        return candidateToBallotsMap.entrySet().stream().min(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static int findBallotCount(Map<Character, List<Ballot>> candidateToBallotsMap) {
        return candidateToBallotsMap.values().stream().mapToInt(List::size).sum();
    }

}
