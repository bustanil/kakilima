package org.inharmonia.kakilima.base.dao;

import org.inharmonia.kakilima.base.domain.User;

public interface UserDao extends BaseDao<User> {

    User findByEmail(String id);

}
