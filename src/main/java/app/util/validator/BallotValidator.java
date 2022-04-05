package app.util.validator;

import java.util.HashSet;
import java.util.Set;

import static app.util.console.ConsoleWriter.printInfo;

public final class BallotValidator {

    public static boolean isValidBallot(final String userPreferenceLine,
                                        final Set<Character> candidateOptions) {
        Set<Character> userPreferenceSet = new HashSet<>();

        char userPreference;
        for (int i = 0; i < userPreferenceLine.length(); i++) {
            userPreference = userPreferenceLine.charAt(i);

            if (!candidateOptions.contains(userPreference)) {
                printInfo(String.format("Ignoring the ballot: %s. Ballot contains the option %s which does not correspond to any candidate.",
                        userPreferenceLine, userPreference));
                printInfo("Please press Enter/Return to continue.");
                return false;
            }

            if (userPreferenceSet.contains(userPreference)) {
                printInfo(String.format("Ignoring the ballot: %s. Ballot contains the option %s more than once.",
                        userPreferenceLine, userPreference));
                printInfo("Please press Enter/Return to continue.");
                return false;
            } else {
                userPreferenceSet.add(userPreference);
            }

        }

        return true;
    }

    private BallotValidator() {
        throw new AssertionError("Can not be instantiated.");
    }
}
