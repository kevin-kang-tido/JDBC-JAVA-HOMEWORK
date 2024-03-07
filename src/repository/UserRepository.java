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
                Statement statement = connection.createStatement();
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
}
