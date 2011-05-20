package org.inharmonia.kakilima.base.service.impl;

import org.apache.commons.lang.Validate;
import org.inharmonia.kakilima.base.dao.CategoryDao;
import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = true)
    private CategoryDao categoryDao;

    @Override
    public List<Category> getFirstLevelCategories() {
        return categoryDao.getFirstLevelCategories();    
    }

    @Override
    public void addCategory(Category category) {
        Validate.notNull(category);
        categoryDao.create(category);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
