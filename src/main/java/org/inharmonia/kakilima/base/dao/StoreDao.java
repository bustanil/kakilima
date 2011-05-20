package org.inharmonia.kakilima.base.dao;

import org.inharmonia.kakilima.base.domain.Store;
import org.inharmonia.kakilima.base.domain.User;

import java.util.List;

public interface StoreDao extends BaseDao<Store> {
    List<Store> getByUser(User user);
}
