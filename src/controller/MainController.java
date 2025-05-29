package controller;

import dto.TodoRecord;
import dto.UserRecord;
import entity.Todo;
import entity.User;
import service.MainService;
import util.Util;

import java.util.Arrays;
import java.util.Optional;

import static util.Util.*;

public class MainController {
    MainService mainService = new MainService();

    public void authMenu() {
        while (true) {
            {
                User user1 = new User("Axmadullo", "997494262", "123456789");
                user1.setId(userId);
                userList[0] = user1;
            }

            System.out.println("Todo Application");

            System.out.println("""
              1. Login
              2. Register
              0. Quit""");
            int option = getNum("Choose an option");

            switch (option) {
                case 1 -> {
                    signIn();
                }
                case 2 -> {
                    signUp();
                }
                case 0 -> {
                    return;
                }
            }
        }

    }

    private void signUp() {
        String name = getText("Enter your name");
        String phoneNumber = getText("Enter your phone number");
        String password = getText("Enter your password");

        UserRecord userRecord = new UserRecord(name, phoneNumber, password);
        System.out.println(userRecord);
        boolean response = mainService.signUp(userRecord);

        if (response) {
            System.out.printf("\n---------------------- Welcome: %s ----------------------\n", name);
            System.out.println();

            mainMenu();
        }else {
            System.out.println("Error occured during register!\n");
        }

    }

    private void signIn() {
        String phoneNumber = getText("Enter your phone number");
        String password = getText("Enter your password");

        UserRecord userRecord = new UserRecord(String.valueOf(Optional.empty()), phoneNumber, password);
        boolean response = mainService.signIn(userRecord);

        if (response) {
            System.out.println("I'm glad to see you again " + mainService.getUserById(userCurrentId));

            mainMenu();
        }else {
            System.out.println("Error occur during log in!");
        }

    }

    private void mainMenu() {
        while (true) {
            System.out.println("""
                1. Create todo
                2. Read todo
                3. Update todo
                4. Delete todo
                """);

            int option = getNum("Choose an option");

            switch (option) {
                case 1 -> {
                    createTodo();
                }
                case 2 -> {
                    getTodo();
                }
                case 3 -> {
                    editTodo();
                }
                case 4 -> {
                    deleteTodo();
                }
                default -> {
                    return;
                }
            }
        }

    }

    private void createTodo() {
        String title = getText("Enter title");
        String description = getText("Enter description");

        if (mainService.createTodo(title, description)) {
            System.out.println("Successfully added!");
        }else {
            System.out.println("Error occur while adding data!");
        }

    }

    private void getTodo() {

        TodoRecord [] todoRecords = mainService.getTodoList();

        for (TodoRecord todoRecord: todoRecords) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("id: " + todoRecord.id());
            System.out.println("Title: " + todoRecord.title());
            System.out.println("Description: " + todoRecord.description());
            System.out.println("Status: " + todoRecord.status());

            System.out.println("------------------------------------------------------------------");
        }
        System.out.println("""
                1. Completed tasks
                2. New tasks
                """);
        int option = getNum("Choose an option");

        switch (option) {
            case 1 -> {
                getCompletedTasks();
            }
            case 2 -> {
                getNewTasks();
            }
            default -> {
                return;
            }
        }

    }

    private void getNewTasks() {
        TodoRecord [] todoRecords = mainService.getTodoList();

        for (TodoRecord todoRecord: todoRecords) {
            if (todoRecord.status().equals(defaultStatus)) {
                System.out.println("------------------------------------------------------------------");

                System.out.println("id: " + todoRecord.id());
                System.out.println("Title: " + todoRecord.title());
                System.out.println("Description: " + todoRecord.description());
                System.out.println("Status: " + todoRecord.status());

                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    private void getCompletedTasks() {
        TodoRecord [] todoRecords = mainService.getTodoList();

        for (TodoRecord todoRecord: todoRecords) {
            if (todoRecord.status().equals(updatedStatus)) {
                System.out.println("------------------------------------------------------------------");

                System.out.println("id: " + todoRecord.id());
                System.out.println("Title: " + todoRecord.title());
                System.out.println("Description: " + todoRecord.description());
                System.out.println("Status: " + todoRecord.status());

                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    private void editTodo() {
        while (true) {
            System.out.println("""
            1. Edit title
            2. Edit description
            3. Edit status""");
            int option = getNum("Choose an option");

            switch (option) {
                case 1 -> {
                    editTitle();
                }
                case 2 -> {
                    editDescription();
                }
                case 3 -> {
                    editCompleting();
                }
                default -> {
                    return;
                }
            }
        }

    }

    private void editTitle() {
        getTodo();
        int todoId = getNum("Which one todos title do you want to change?");
        String title = getText("Enter title");

        TodoRecord todoRecord = mainService.updateTodosTitle(todoId, title);

        System.out.println(todoRecord);

    }

    private void editDescription() {
        getTodo();

        int todoId = getNum("Which one todos description do you want to change?");
        String description = getText("Enter description");

        TodoRecord todoRecord = mainService.updateTodosDescription(todoId, description);

        System.out.println(todoRecord);
    }

    public void editCompleting() {
        getTodo();

        int todoId = getNum("Which one todos completing do you want to change?");

        TodoRecord todoRecord = mainService.updateTodosCompleting(todoId);

        System.out.println(todoRecord);
    }

    private void deleteTodo() {
        getTodo();
        int todoId = getNum("Which one todos completing do you want to change?");
        boolean deleteToById = mainService.deleteToById(todoId);

        if (deleteToById) {
            System.out.println("Successfully deleted!");
            return;
        }else {
            System.out.println("Id is not found!");
        }
    }

}
