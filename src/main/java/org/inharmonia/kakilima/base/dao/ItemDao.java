package org.inharmonia.kakilima.base.dao;

import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemDao extends BaseDao<Item>{

     List<Item> getItemsByCategoryId(Category category);
}
