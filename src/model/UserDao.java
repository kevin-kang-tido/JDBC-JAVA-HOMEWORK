package model;

import java.util.List;

public interface UserDao {
     List<User> getAllUser();
     User searchUserById(Integer id);
//     User createUser(User user);

     // delete User
     User deleteUser(User user);

     // update
     User UpdateUser( User user);


}


