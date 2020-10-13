package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    Util util =  new Util();
    Statement statement = null;

    public void createUsersTable() {
        try {
            util.connection();
            statement = util.conn.createStatement();
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
            statement = util.conn.createStatement();
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
        try {
            util.connection();
            statement = util.conn.createStatement();
            String get = "SELECT * FROM base";
            ResultSet resultSet = statement.executeQuery(get);

            while (resultSet.next()){
                String name = resultSet.getNString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                System.out.println(name + " " + lastname + " " + age);
            }
            statement.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
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
