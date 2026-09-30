import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus status;

    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = TaskStatus.TODO;
    }

    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public Date getDueDate() {
        return this.dueDate;
    }
    public TaskStatus getStatus() {
        return this.status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
