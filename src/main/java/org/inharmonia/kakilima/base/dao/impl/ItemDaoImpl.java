package org.inharmonia.kakilima.base.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.inharmonia.kakilima.base.dao.ItemDao;
import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl extends BaseDaoImpl<Item> implements ItemDao {
    @Override
    public List<Item> getItemsByCategoryId(Category category) {
        return createCriteria().add(Restrictions.eq("category", category)).list();
    }
}
