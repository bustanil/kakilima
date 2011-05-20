package org.inharmonia.kakilima.base.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.inharmonia.kakilima.base.dao.StoreDao;
import org.inharmonia.kakilima.base.domain.Store;
import org.inharmonia.kakilima.base.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"unchecked"})
@Repository
public class StoreDaoImpl extends BaseDaoImpl<Store> implements StoreDao {

    @Override
    public List<Store> getByUser(User user) {
        return  createCriteria().add(Restrictions.eq("user", user)).list();
    }
    
}
