    package service;

    import dto.TodoRecord;
    import dto.UserRecord;
    import entity.Todo;
    import entity.User;

    import static util.Util.*;

    public class MainService {

        public boolean signUp(UserRecord userRecord) {
            for (User user: userList) {
                if (user != null && user.getPhoneNumber().equals(userRecord.phoneNumber()) ) {
                    return false;
                }
            }

            User user = new User(userRecord.name(), userRecord.phoneNumber(), userRecord.password());
            user.setId(userId++);

            for (int i = 0; i < userList.length; i++) {
                if (userList[i] == null) {
                    userList[i] = user;
                    break;
                }
            }

            return true;
        }

        public boolean signIn(UserRecord userRecord) {
            for (User user: userList) {
                if (user != null && user.getPhoneNumber().equals(userRecord.phoneNumber()) ) {
                    userCurrentId = user.getId();
                    return true;
                }
            }

            return false;
        }

        public String getUserById(int currentUserId) {
            for (User user : userList) {
                if (user != null && user.getId() == currentUserId) {
                    return user.getName();
                }
            }

            return null;
        }


        public boolean createTodo(String title, String description) {
            for (int i = 0; i < todoList.length; i++) {
                if (todoList[i] == null) {
                    Todo todo = new Todo(title, description, defaultStatus);
                    todo.setId(todoId++);
                    todo.setUserId(userCurrentId);
                    todoList[i] = todo;
                    return true;
                }
            }

            return false;
        }

        public TodoRecord[] getTodoList() {
            int counter = 0;

            for (Todo todo: todoList) {
                if (todo != null && todo.getUserId() == userCurrentId) {
                    counter++;
                }
            }

            TodoRecord [] todoArray = new TodoRecord[counter];

            int index = 0;

            for (Todo todo: todoList) {
                if (todo != null && todo.getUserId() == userCurrentId ) {
                    TodoRecord todoRecord = new TodoRecord(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getStatus());
                    todoArray[index++] = todoRecord;
                }
            }

            return todoArray;
        }

        public TodoRecord updateTodosTitle(int todoId, String title) {
            for (Todo todo : todoList) {
                if (todo != null && todo.getId() == todoId) {
                    todo.setTitle(title);
                    return new TodoRecord(todo.getId(), title, todo.getDescription(), todo.getStatus());
                }
            }

            return null;
        }

        public TodoRecord updateTodosDescription(int todoId, String description) {
            for (Todo todo : todoList) {
                if (todo != null && todo.getId() == todoId) {
                    todo.setDescription(description);
                    return new TodoRecord(todo.getId(), todo.getTitle(), description, todo.getStatus());
                }
            }

            return null;
        }


        public TodoRecord updateTodosCompleting(int todoId) {
            for (Todo todo : todoList) {
                if (todo != null && todo.getId() == todoId) {
                    todo.setStatus(updatedStatus);
                    return new TodoRecord(todo.getId(), todo.getTitle(), todo.getDescription(), updatedStatus);
                }
            }

            return null;
        }

        public boolean deleteToById(int todoId) {
            for (int i = 0; i < todoList.length; i++) {
                if (todoList[i] != null && todoList[i].getId() == todoId) {
                    todoList[i] = null;
                    return true;
                }
            }
            return false;
        }
    }
