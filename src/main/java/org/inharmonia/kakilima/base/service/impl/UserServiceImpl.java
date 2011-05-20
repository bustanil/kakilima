package org.inharmonia.kakilima.base.service.impl;

import org.apache.commons.lang.Validate;
import org.inharmonia.kakilima.base.dao.UserDao;
import org.inharmonia.kakilima.base.domain.User;
import org.inharmonia.kakilima.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = true)
    private UserDao userDao;

    @Override
    public User validateUser(String id, String password) {
        Validate.notNull(id);
        Validate.notNull(password);
        User user = userDao.findByEmail(id);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        Validate.notNull(user);
        userDao.create(user);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        User user = userDao.findByEmail(email);
        return user == null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
