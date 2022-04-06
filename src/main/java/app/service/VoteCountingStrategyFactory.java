package app.service;

import java.util.Map;

public class VoteCountingStrategyFactory {
    private static final Map<String, VoteCounter> VOTE_COUNTING_STRATEGY_MAP = Map.of(
            "preferentialVoteCounter", new PreferentialVoteCounter());

    public VoteCounter provideVoteCounter(final String votingStrategy) {
        return VOTE_COUNTING_STRATEGY_MAP.get(votingStrategy);
    }
}
