package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    Util util =  new Util();

    public void createUsersTable() {
        try {
            util.connection();
            Statement statement = util.conn.createStatement();
            String table = "CREATE TABLE IF NOT EXISTS base (Id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastname VARCHAR(20) NOT NULL, " +
                    "age INT NOT NULL)";
            statement.executeUpdate(table);
            statement.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            util.connection();
            Statement statement = util.conn.createStatement();
            String tableDrop = "DROP TABLE if EXISTS base";
            statement.executeUpdate(tableDrop);
            statement.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            util.connection();
            PreparedStatement statement = util.conn.prepareStatement ("INSERT INTO base (name, lastname, age) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            statement.close();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            util.connection();
            PreparedStatement statement = util.conn.prepareStatement("DELETE FROM base WHERE Id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> results = new ArrayList<>();

        try {
            util.connection();
            String sql = "SELECT * FROM base";
            PreparedStatement statement = util.conn.prepareStatement(sql) ;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                System.out.println(user.toString());
                results.add(user);
            }
            statement.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
        return results;
    }

    public void cleanUsersTable() {
        try {
            Statement statement;
            util.connection();
            statement = util.conn.createStatement();
            String tableClean = "TRUNCATE TABLE base ";
            statement.executeUpdate(tableClean);
            statement.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }
}
