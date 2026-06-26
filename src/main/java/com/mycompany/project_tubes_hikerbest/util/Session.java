/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes_hikerbest.util;

/**
 *
 * @author V16_E
 */
import com.mycompany.project_tubes_hikerbest.model.User;

/**
 * Session menyimpan data user yang sedang login (singleton).
 * Dipakai oleh semua panel/view agar tahu siapa yang sedang login
 * tanpa perlu mengoper parameter User di setiap constructor.
 */
public class Session {
    private static User currentUser;

    private Session() {
        // mencegah instansiasi
    }

    public static void login(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return isLoggedIn() && "admin".equalsIgnoreCase(currentUser.getRole());
    }

    public static void logout() {
        currentUser = null;
    }
}
