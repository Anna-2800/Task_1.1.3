package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Vova", "Boyko", (byte) 27);
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 19);
        userDaoJDBC.saveUser("Anna", "Maximova", (byte) 20);
        userDaoJDBC.saveUser("Masha", "Petrova", (byte) 15);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
