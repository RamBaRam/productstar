import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Project> projects;

    public TaskManager() {
        this.projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        if (project.getTasks().isEmpty()) throw  new IllegalArgumentException("No tasks in this project");
        projects.add(project);
    }

    public void removeProject(Project project) {
        if (project.getTasks().isEmpty()) throw  new IllegalArgumentException("No tasks in this project");
        projects.remove(project);
    }

    public List<Project> getProjects(){
        return this.projects;
    }

    public void getAllProjects() {
        int count = 0;
        while (count != this.projects.size()){
            Project project = this.projects.get(count);
            System.out.println("Проект № " + (count + 1));
            project.getAllTasks();
            count++;
        }
    }
}
