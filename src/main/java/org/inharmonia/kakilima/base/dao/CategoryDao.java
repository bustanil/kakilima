package org.inharmonia.kakilima.base.dao;

import org.inharmonia.kakilima.base.domain.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {

    List<Category> getFirstLevelCategories();

}
