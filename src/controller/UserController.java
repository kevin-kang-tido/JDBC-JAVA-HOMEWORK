package controller;

import model.User;
import model.UserDao;
import model.UserDaoImp;
import repository.UserRepository;
import view.View;

import java.util.List;

public class UserController {

    private static final UserDao userDao = new UserDaoImp();
    private static  final View userView = new View();
    static List<User> users = userDao.getAllUser();

    public List<User> getAllUser(){
        return  userDao.getAllUser();

    }
    public User searchUserBYId(Integer id){
        User user = userDao.searchUserById(id);
        return user;
    }
    // create User
    public void createUser(){
       User user = userView.CreateUser();
        UserRepository.createUser(user);

    }
    // update
//    public  void
    // Deleted user
    public void deleteUser(){
            User user = userView.deleteUser(users);
            userDao.deleteUser(user);
    }
}
