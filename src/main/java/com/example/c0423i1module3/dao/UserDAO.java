package com.example.c0423i1module3.dao;

import com.example.c0423i1module3.model.User;
import com.example.c0423i1module3.model.enums.EGender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends DatabaseConnection {
    private final String SELECT_ALL_USERS = "SELECT * FROM `users`";
    private final String UPDATE_USER = "UPDATE `users` SET `name` = ?, `phone` = ?, `avatar` = ?, `gender` = ?, `dob` = ?, `cover_picture` = ? WHERE (`id` = ?);";

    private final String INSERT_USERS = "INSERT INTO `users` (`name`, `phone`, `email`, `avatar`, `gender`, `dob`, `cover_picture`) \n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_BY_ID = "SELECT * FROM `users` WHERE `id` = ?"; // show Edit

    private final String DELETE_BY_ID = "DELETE FROM `users` WHERE (`id` = ?)";


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                users.add(getUserByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void insertUser(User user){
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(INSERT_USERS)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPhone());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getAvatar());
            preparedStatement.setString(5,user.getGender().toString());
            preparedStatement.setString(6,user.getDob());
            preparedStatement.setString(7,user.getCoverPicture());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(UPDATE_USER)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPhone());
            preparedStatement.setString(3,user.getAvatar());
            preparedStatement.setString(4,user.getGender().toString());
            preparedStatement.setString(5,user.getDob());
            preparedStatement.setString(6,user.getCoverPicture());
            preparedStatement.setLong(7,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(Long id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(getUserByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteById(Long id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);

           preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserByResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String avatar = rs.getString("avatar");
        String dob = rs.getString("dob");
        String coverPicture = rs.getString("cover_picture");
        String gender = rs.getString("gender");
        return new User(id, name, phone,email, avatar, EGender.valueOf(gender), Date.valueOf(dob), coverPicture);
    }
}
