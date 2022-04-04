package app.util.validator;

public final class StringValidator {

    public static boolean isNullOrEmpty(final String value) {
        return (value == null || value.equals(""));
    }

    private StringValidator() {
        throw new AssertionError("Can not be instantiated");
    }
}
