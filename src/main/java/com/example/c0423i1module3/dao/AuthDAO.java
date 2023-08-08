package com.example.c0423i1module3.dao;

import com.example.c0423i1module3.model.Login;
import com.example.c0423i1module3.model.User;
import com.example.c0423i1module3.util.AppUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthDAO extends DatabaseConnection{

    public void register(Login login){
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(AppUtil.buildInsertSql("logins", login))) {
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Login> findByUsername(String username) {
        final String FIND_BY_ID = "SELECT * FROM `logins` WHERE `username` = ?";
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_BY_ID)) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(AppUtil.getObjectFromResultSet(rs, Login.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
