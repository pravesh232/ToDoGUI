
// menu and logic

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoApp {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> markTaskDone();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========== TO-DO MENU ==========");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Done");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ignored) {}
        return choice;
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String desc = scanner.nextLine();
        tasks.add(new Task(desc));
        System.out.println("Task added.");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\n---- Your Tasks ----");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTaskDone() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to mark as done: ");
        int index = getChoice() - 1;
        if (isValidIndex(index)) {
            tasks.get(index).markDone();
            System.out.println("Task marked as done.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to delete: ");
        int index = getChoice() - 1;
        if (isValidIndex(index)) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
