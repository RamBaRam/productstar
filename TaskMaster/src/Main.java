import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Task t1 = new Task("Сделать дизайн", "Нарисовать макеты главного экрана", new Date());
        Task t2 = new Task("Настроить БД", "Создать таблицы и связи", new Date());
        Task t3 = new Task("API авторизации", "Сделать регистрацию/вход", new Date());
        Task t4 = new Task("Документация", "Описать эндпоинты в README", new Date());

        Project project1 = new Project();
        project1.addTask(t1);
        project1.addTask(t2);
        project1.addTask(t3);
        project1.addTask(t4);

        System.out.println("=== Список задач проекта (до изменений) ===");
        project1.getAllTasks();

        System.out.println("\n=== Изменяем статусы задач ===");
        project1.assign(t1);
        project1.start(t2);
        project1.complete(t3);
        System.out.println("\n=== Список задач проекта (после изменения статусов) ===");
        project1.getAllTasks();

        System.out.println("\n=== Сортировка задач по title ===");
        project1.getTasks().sort(new Project.TaskComparator("title"));
        project1.getAllTasks();

        System.out.println("\n=== Сортировка задач по description ===");
        project1.getTasks().sort(new Project.TaskComparator("description"));
        project1.getAllTasks();

        TaskManager manager = new TaskManager();
        manager.addProject(project1);

        Project project2 = new Project();
        Task p2t1 = new Task("Тесты", "Написать unit-тесты", new Date());
        Task p2t2 = new Task("CI", "Настроить GitHub Actions", new Date());
        project2.addTask(p2t1);
        project2.addTask(p2t2);

        project2.start(p2t1);
        manager.addProject(project2);

        System.out.println("\n=== ВСЕ ПРОЕКТЫ В TASK MANAGER ===");
        manager.getAllProjects();

        System.out.println("\n=== Статистика ===");
        System.out.println("Всего проектов: " + Project.ProjectStats.getTotalProjects());
        System.out.println("Всего задач: " + Project.ProjectStats.getTotalTasks());
    }
}