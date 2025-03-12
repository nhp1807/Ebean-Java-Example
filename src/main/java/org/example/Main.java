package org.example;

import io.ebean.Database;
import io.ebean.DB;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Database database = DB.getDefault();

    public static void main(String[] args) {
        // Chạy hàm lập lịch để thêm user tự động
        startUserScheduler();

        // Tìm user theo ID
//        List<User> users = findAllUsers();
//        for (User user : users) {
//            System.out.println("User: " + user);
//        }

    }

    private static void startUserScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable insertUsersTask = () -> {
            try {
                System.out.println("Đang tạo 5 user mới...");

                for (int i = 0; i < 5; i++) {
                    User user = new User();
                    user.setName("User_" + ThreadLocalRandom.current().nextInt(1000, 9999));
                    user.setEmail("user" + ThreadLocalRandom.current().nextInt(1000, 9999) + "@example.com");

                    database.save(user);
                }

                System.out.println("Đã tạo xong 5 user!");
            } catch (Exception e) {
                System.err.println("Lỗi khi tạo user: " + e.getMessage());
            }
        };

        scheduler.scheduleAtFixedRate(insertUsersTask, 0, 10, TimeUnit.SECONDS);
    }

    private static void updateUser(int id, String newName, String newEmail) {
        User user = database.find(User.class, id);
        if (user != null) {
            user.setName(newName);
            user.setEmail(newEmail);
            database.update(user);
            System.out.println("Đã cập nhật user có ID: " + id);
        } else {
            System.out.println("Không tìm thấy user có ID: " + id);
        }
    }

    private static void deleteUser(int id) {
        User user = database.find(User.class, id);
        if (user != null) {
            database.delete(user);
            System.out.println("Đã xóa user có ID: " + id);
        } else {
            System.out.println("Không tìm thấy user có ID: " + id);
        }
    }

    private static List<User> findAllUsers() {
        return database.find(User.class).findList();
    }

    private static User findUserById(int id) {
        return database.find(User.class, id);
    }
}
