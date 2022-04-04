package app.util.validator;

import java.util.Collection;

public final class CollectionValidator {

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    private CollectionValidator() {
        throw new AssertionError("Can not be instantiated.");
    }
}
