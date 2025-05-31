package controller;

import dto.TodoRecord;
import dto.UserRecord;
import entity.User;
import enums.TodoStatus;
import service.MainService;

import java.util.Optional;

import static util.Util.*;

public class MainController {
    MainService mainService = new MainService();

    public void startMenu() {
        {
            User user1 = new User("Axmadullo", "997494262", "123456789");
            user1.setId(userId);
            userList[0] = user1;
        }

        while (true) {

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
                5. Search todo
                """);

            int option = getNum("Choose an option");

            switch (option) {
                case 1 -> {
                    createTodo();
                }
                case 2 -> {
                    viewTodosMenu();
                }
                case 3 -> {
                    editTodo();
                }
                case 4 -> {
                    deleteTodo();
                }
                case 5 -> {
                    searchResult();
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

    private void viewTodosMenu() {
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
        TodoRecord [] todoRecords = mainService.getNewTodos();

        for (TodoRecord todoRecord: todoRecords) {
            String status = TodoStatus.NEW.name();

            if (todoRecord.status().equals(status)) {
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
        TodoRecord [] completedTasks = mainService.getCompletedTasks();

        for (TodoRecord todoRecord: completedTasks) {
            String status = TodoStatus.COMPLETED.name();

            if (todoRecord.status().equals(status)) {
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
                    markTodoAsCompleted();
                }
                default -> {
                    return;
                }
            }
        }

    }

    private void editTitle() {
        viewTodosMenu();
        int todoId = getNum("Which one todos title do you want to change?");
        String title = getText("Enter title");

        TodoRecord todoRecord = mainService.updateTodosTitle(todoId, title);

        if (todoRecord != null) {
            System.out.println(todoRecord);
        }else {
            System.out.println("Empty data");
        }

    }

    private void editDescription() {
        viewTodosMenu();

        int todoId = getNum("Which one todos description do you want to change?");
        String description = getText("Enter description");

        TodoRecord todoRecord = mainService.updateTodosDescription(todoId, description);

        if (todoRecord != null) {
            System.out.println(todoRecord);
        }else {
            System.out.println("Empty data");
        }
    }

    public void markTodoAsCompleted() {
        viewTodosMenu();

        int todoId = getNum("Which one todos completing do you want to change?");

        TodoRecord todoRecord = mainService.updateTodosCompleting(todoId);

        if (todoRecord != null) {
            System.out.println(todoRecord);
        }

    }

    private void deleteTodo() {
        viewTodosMenu();
        int todoId = getNum("Which one todos completing do you want to change?");
        boolean deleteToById = mainService.deleteToById(todoId);

        if (deleteToById) {
            System.out.println("Successfully deleted!");
            return;
        }else {
            System.out.println("Id is not found!");
        }
    }

    private void searchResult() {
        String req = getText("Enter keyword to find todo");

        TodoRecord [] searchResultArray = mainService.searchTodo(req);

        if (searchResultArray.length == 0) {
            System.out.println("Not found results");
            return;
        }else {
            System.out.println("Search menu: ");
        }

        for (TodoRecord todo: searchResultArray) {
            if (todo != null) {
                System.out.println("Founded results: ");

                System.out.println("------------------------------------------------------------------");

                System.out.println("id: " + todo.id());
                System.out.println("Title: " + todo.title());
                System.out.println("Description: " + todo.description());
                System.out.println("Status: " + todo.status());

                System.out.println("------------------------------------------------------------------");
            }
        }

    }
}
