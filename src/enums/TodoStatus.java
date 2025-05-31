package enums;

public enum TodoStatus {
    NEW("NEW"),
    COMPLETED("COMPLETED");

    private final String description;

    TodoStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
