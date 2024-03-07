package model;

import repository.UserRepository;

import java.util.List;

public class UserDaoImp implements UserDao{
    @Override
    public List<User> getAllUser() {

        return UserRepository.getAllUser();
    }

    @Override
    public User searchUserById(Integer id) {
         return UserRepository.searchUserById(id);

    }


}
