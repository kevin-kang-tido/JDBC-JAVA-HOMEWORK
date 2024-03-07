package controller;

import model.User;
import model.UserDao;
import model.UserDaoImp;

import java.util.List;

public class UserController {
    private static final UserDao userDao = new UserDaoImp();
    public List<User> getAllUser(){

        return  userDao.getAllUser();

    }
    public User searchUserBYId(Integer id){

        User user = userDao.searchUserById(id);
        return user;
    }


}
