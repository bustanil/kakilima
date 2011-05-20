package org.inharmonia.kakilima.base.service;

import org.inharmonia.kakilima.base.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getFirstLevelCategories();

    void addCategory(Category category);

}
