package model;

import java.util.List;

public interface UserDao {
     List<User> getAllUser();
     User searchUserById(Integer id);
}
