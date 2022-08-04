package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String createSQlTable = "CREATE TABLE IF NOT EXISTS `mukashema`.`user` " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age TINYINT, " +
                "PRIMARY KEY (id))";

        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement(createSQlTable)){

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger("Create table").log(Level.WARNING, "Во время СОЗДАНИЯ таблицы" +
                    "произошла ошибка.");
        }
    }

    public void dropUsersTable() {

        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("DROP TABLE IF EXISTS `mukashema`.`user`")){

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger("Drop table").log(Level.WARNING, "Во время УДАЛЕНИЯ таблицы" +
                    "произошла ошибка.");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("insert into `mukashema`.`user` values (1, ?, ?, ?)")){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем " + name + " добавлен в БД");

        } catch (SQLException e) {
            Logger.getLogger("Save user").log(Level.WARNING, "Во время СОХРАНЕНИЯ user-a" +
                    "произошла ошибка.");
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("DELETE FROM `mukashema`.`user` WHERE id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger("Remove user").log(Level.WARNING, "Во время УДАЛЕНИЯ user-a по ID " + id +
                    "произошла ошибка.");
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("SELECT name, lastName, age FROM `mukashema`.`user`")){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"),
                                  resultSet.getString("lastName"),
                                  resultSet.getByte("age")));
            }

        } catch (SQLException e) {
            Logger.getLogger("Get all user").log(Level.WARNING, "Во время ПОЛУЧЕНИЯ всех user-ov" +
                    "произошла ошибка.");
        }

        return list;

    }

    public void cleanUsersTable() {

        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("DELETE FROM `mukashema`.`user`")){
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger("Clean table").log(Level.WARNING, "Во время ОЧИСТКИ таблицы" +
                    "произошла ошибка.");
        }

    }
}
