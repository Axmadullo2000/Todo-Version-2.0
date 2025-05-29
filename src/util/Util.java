package util;

import dto.TodoRecord;
import entity.Todo;
import entity.User;

import java.util.Scanner;

public class Util {
    public static int userId = 1;
    public static int todoId = 1;

    public static int userCurrentId = 0;

    public static String defaultStatus = "NEW";
    public static String updatedStatus = "COMPLETED";

    public static User [] userList = new User[100];
    public static Todo[] todoList = new Todo[100];

    public static Scanner strScanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);

    public static int getNum(String text) {
        System.out.print(text + ": ");
        return intScanner.nextInt();
    }

    public static String getText(String text) {
        System.out.print(text + ": ");
        return strScanner.nextLine();
    }
}
