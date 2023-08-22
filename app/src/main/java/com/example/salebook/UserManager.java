package com.example.salebook;

import com.example.salebook.model.User;

public class UserManager {
    private static UserManager instance;
    private User currentUser; // Người dùng đang đăng nhập

    private UserManager() {
        // Private constructor để ngăn việc tạo đối tượng từ bên ngoài lớp
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}

