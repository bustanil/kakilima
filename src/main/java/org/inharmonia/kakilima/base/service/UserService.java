package org.inharmonia.kakilima.base.service;

import org.inharmonia.kakilima.base.domain.User;

public interface UserService {

    /**
     * Validate user information
     * @param username
     * @param password
     * @return the validated user, null if no such user is found
     */
    User validateUser(String username, String password);

    void addUser(User user);

    boolean isEmailAvailable(String email);
}
