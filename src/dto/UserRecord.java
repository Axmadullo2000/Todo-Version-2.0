package dto;

public record UserRecord(String name, String phoneNumber, String password) {
    public UserRecord {
        if (name.length() < 2 || phoneNumber.length() < 9 || password.length() < 8) {
            throw new IllegalArgumentException("Incorrect input fields error!");
        }
    }
}
