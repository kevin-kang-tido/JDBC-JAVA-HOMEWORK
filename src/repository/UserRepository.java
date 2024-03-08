package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class UserRepository {
    public static List<User> getAllUser(){
        List<User> users = new ArrayList<>();
        PropertiesLoader.loaderProperties();
        String sql = "SELECT * FROM users";
        try(
                Connection connection = DriverManager.getConnection(
                     PropertiesLoader.properties.getProperty("database_url"),
                     PropertiesLoader.properties.getProperty("database_username"),
                     PropertiesLoader.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while (resultSet.next()){
                users.add(
                        new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_password"),
                                resultSet.getString("user_email"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getString("user_uuid"),
                                resultSet.getBoolean("is_verified")
                        )
                );
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

     return users;
    }
    // create another method for feature search
    public static User searchUserById(Integer id){

        String sql = "SELECT * FROM users WHERE user_id= ?";

        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
//                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    return  new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_password"),
                            resultSet.getString("user_email"),
                            resultSet.getBoolean("is_deleted"),
                            resultSet.getString("user_uuid"),
                            resultSet.getBoolean("is_verified")
                    );
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }catch (Exception e){
            System.out.println("Here is error : "+e.getMessage());
        }
        return null;
    }
    public static void createUser(User user){
        String sql = """
                    INSERT INTO users (user_name,user_password,is_deleted,user_email,user_uuid,is_verified)
                    VALUES (?,?,?,?,?,?)
        """;
        PropertiesLoader.loaderProperties();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")

                );
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);


        ) {
          preparedStatement.setString(1, user.getUser_name());
          preparedStatement.setString(2,user.getUser_password());
          preparedStatement.setBoolean(3,user.getIs_deleted());
                  preparedStatement.setString(4,user.getUser_email());
                  preparedStatement.setString(5, user.getUser_uuid());
            preparedStatement.setBoolean(6,user.getIs_verified());


            int affectedRTow = preparedStatement.executeUpdate();
            if (affectedRTow>0){
                System.out.println("Create User is successfully!!!");
                System.out.println("=".repeat(90));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//
//        return null;
    }
    public static User deleteUser(User user){
        String sql = "DELETE  FROM users WHERE user_id =?" ;
        PropertiesLoader.loaderProperties();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setInt(1,user.getUser_id());

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0 ){
                System.out.println("User deleted successfully");
            } else {
                System.out.println("User not found in database");
            }

//            preparedStatement.setInt(1,user.getUser_id());
//            int rowsDeleted = preparedStatement.executeUpdate();
//            if (rowsDeleted > 0 ){
//                System.out.println("User deleted successfully");
//            } else {
//                System.out.println("User not found in database");
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static User updateUser(User user) {
        String sql = "UPDATE users SET user_name = ?, user_password = ?," +
                " is_deleted = ?, user_email = ?, user_uuid = ?, is_verified = ? WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getUser_password());
            preparedStatement.setBoolean(3, user.getIs_deleted());
            preparedStatement.setString(4, user.getUser_email());
            preparedStatement.setString(5, user.getUser_uuid());
            preparedStatement.setBoolean(6, user.getIs_verified());
            preparedStatement.setInt(7, user.getUser_id());

            int update = preparedStatement.executeUpdate();

            if (update > 0) {
                System.out.println("User updated successfully");
                return user;
            } else {
                System.out.println("User not found or updated.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            // Log the exception or handle it more appropriately
            return null;
        }
    }


}
