package api.portfolio.domain.enums;

@SuppressWarnings("ALL")
public enum ExperienceType {
    FULL_TIME,
    PART_TIME,
    CONTRACT,
    FREELANCE;

    public static boolean isValid(String value) {
        try {
            ExperienceType.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
