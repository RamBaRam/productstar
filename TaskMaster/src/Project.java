import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Project implements Manageable {

    private final List<Task> tasks;

    public Project() {
        this.tasks = new ArrayList<>();
        ProjectStats.projectCreated();
    }

    public void addTask(Task task){
        if(task.getTitle().isBlank()) throw  new IllegalArgumentException();
        if(task.getDescription().isBlank()) throw new IllegalArgumentException();
        this.tasks.add(task);
        ProjectStats.taskAdded();
    }

    public void removeTask(Task task){
        if(task.getTitle().isBlank()) throw  new IllegalArgumentException();
        if(task.getDescription().isBlank()) throw new IllegalArgumentException();
        this.tasks.remove(task);
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

    public void getAllTasks(){
        for (Task task : this.tasks) {
            System.out.println(task.getTitle() + " " + task.getDescription() + " " + task.getStatus().name());
        }
    }

    @Override
    public void assign(Task task){
        if(task.getTitle().isBlank()) throw  new IllegalArgumentException();
        if(task.getDescription().isBlank()) throw new IllegalArgumentException();
        task.setStatus(TaskStatus.TODO);
        System.out.println("Задача: " + task.getTitle() + " - статус изменён: " + task.getStatus().name());
    }

    @Override
    public void start(Task task){
        if(task.getTitle().isBlank()) throw  new IllegalArgumentException();
        if(task.getDescription().isBlank()) throw new IllegalArgumentException();
        task.setStatus(TaskStatus.IN_PROGRESS);
        System.out.println("Задача: " + task.getTitle() + " - статус изменён: " + task.getStatus().name());
    }

    @Override
    public void complete(Task task){
        if(task.getTitle().isBlank()) throw  new IllegalArgumentException();
        if(task.getDescription().isBlank()) throw new IllegalArgumentException();
        task.setStatus(TaskStatus.COMPLETED);
        System.out.println("Задача: " + task.getTitle() + " - статус изменён: " + task.getStatus().name());
    }

    public static class TaskComparator implements Comparator<Task> {

        private final String criteria;

        public TaskComparator(String criteria) {
            this.criteria = criteria;
        }

        @Override
        public int compare(Task o1, Task o2) {

            if(criteria.equalsIgnoreCase("title")) {
                return o1.getTitle().compareTo(o2.getTitle());
            }

            if(criteria.equalsIgnoreCase("description")) {
                return o1.getDescription().compareTo(o2.getDescription());
            }

            return 0;
        }
    }

    public static class ProjectStats {

        private static int totalProjects = 0;
        private static int totalTasks = 0;


        public static void projectCreated() {
            totalProjects++;
        }

        public static void taskAdded() {
            totalTasks++;
        }

        public static int getTotalProjects() {
            return totalProjects;
        }

        public static int getTotalTasks() {
            return totalTasks;
        }
    }

}
