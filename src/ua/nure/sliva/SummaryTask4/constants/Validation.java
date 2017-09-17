package ua.nure.sliva.SummaryTask4.constants;

public abstract class Validation {
    public static final String LOGIN_PATTERN = "[A-Za-z0-9_]{5,24}";
    public static final String PASSWORD_PATTERN = "[A-Za-z0-9_]{5,24}";
    public static final String EMAIL_PATTERN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}";
    public static final String NAME_PATTERN = "[a-zA-Z0-9]{1,20}";
}
