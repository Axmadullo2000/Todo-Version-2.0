package entity;

public class Todo extends Parent {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String title;
    private String description;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Todo(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTodoInfo() {
        return "\nid: %d, title: %s, description: %s\n".formatted(getId(), title, description);
    }
}
