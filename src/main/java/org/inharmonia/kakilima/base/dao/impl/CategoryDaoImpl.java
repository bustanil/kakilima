package org.inharmonia.kakilima.base.dao.impl;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.inharmonia.kakilima.base.dao.CategoryDao;
import org.inharmonia.kakilima.base.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    @Override
    public List<Category> getFirstLevelCategories() {
        return createCriteria().add(Restrictions.isNull("parent")).addOrder(Order.asc("name")).list();
    }
    
}
