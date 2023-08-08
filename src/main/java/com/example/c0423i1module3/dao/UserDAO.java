package com.example.c0423i1module3.dao;

import com.example.c0423i1module3.model.Role;
import com.example.c0423i1module3.model.User;
import com.example.c0423i1module3.model.enums.EGender;
import com.example.c0423i1module3.service.dto.PageableRequest;
import com.example.c0423i1module3.service.dto.enums.ESortType;
import com.example.c0423i1module3.util.AppUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends DatabaseConnection {
    private final String SELECT_ALL_USERS = "SELECT u.*,r.name `role.name`, r.id as `role.id`  FROM `users` u LEFT JOIN " +
            "`roles` r on u.role_id = r.id  WHERE u.`name` like '%s' OR u.`phone` LIKE '%s' Order BY %s %s LIMIT %s OFFSET %s";
    private final String SELECT_TOTAL_RECORDS = "SELECT COUNT(1) as cnt  FROM `users` u LEFT JOIN " +
            "`roles` r on u.role_id = r.id  WHERE u.`name` like '%s' OR u.`phone` LIKE '%s'";

    private final String FIND_BY_ID = "SELECT u.*,r.name role_name  FROM " +
            "`users` u LEFT JOIN `roles` r on u.role_id = r.id WHERE u.`id` = ?"; // show Edit

    private final String DELETE_BY_ID = "DELETE FROM `users` WHERE (`id` = ?)";


    public List<User> findAll(PageableRequest request) {
        List<User> users = new ArrayList<>();
        String search = request.getSearch();
        if(request.getSortField() == null){
            request.setSortField("id");
        }
        if(request.getSortType() == null){
            request.setSortType(ESortType.DESC);
        }
        if(search == null){
            search = "%%";
        }else {
            search = "%" + search + "%";
        }
        var offset = (request.getPage() - 1) * request.getLimit();
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(String.format(SELECT_ALL_USERS, search, search,
                             request.getSortField(), request.getSortType(), request.getLimit(), offset))) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            //

            while (rs.next()) {
                users.add(AppUtil.getObjectFromResultSet(rs, User.class));
            }
            PreparedStatement statementTotalRecords = connection
                    .prepareStatement(String.format(SELECT_TOTAL_RECORDS, search, search));
            ResultSet resultSetOfTotalRecords = statementTotalRecords.executeQuery();
            if(resultSetOfTotalRecords.next()){
                int totalPage =
                        (int) Math.ceil(resultSetOfTotalRecords.getDouble("cnt")/request.getLimit());
                request.setTotalPage(totalPage);
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
                     .prepareStatement(AppUtil.buildInsertSql("users", user))) {
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(AppUtil.buildUpdateSql("users", user))) {
            System.out.println(preparedStatement);

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
                return Optional.of(AppUtil.getObjectFromResultSet(rs, User.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
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
        String roleName = rs.getString("role_name");
        Long roleId = rs.getLong("role_id");
        Role role = new Role(roleId, roleName);
        return new User(id, name, phone,email, avatar, EGender.valueOf(gender), Date.valueOf(dob), coverPicture, role);
    }
}
