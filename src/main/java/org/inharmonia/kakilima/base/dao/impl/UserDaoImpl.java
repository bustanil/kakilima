package org.inharmonia.kakilima.base.dao.impl;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.inharmonia.kakilima.base.dao.UserDao;
import org.inharmonia.kakilima.base.domain.User;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.*;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User findByEmail(String id) {
        return (User) createCriteria().add(
                eq("email", id)
        ).uniqueResult();
    }
}
