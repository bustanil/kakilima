package org.inharmonia.kakilima.base.service.impl;

import org.inharmonia.kakilima.base.dao.CategoryDao;
import org.inharmonia.kakilima.base.dao.ItemDao;
import org.inharmonia.kakilima.base.dao.StoreDao;
import org.inharmonia.kakilima.base.dao.UserDao;
import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.domain.Item;
import org.inharmonia.kakilima.base.domain.Store;
import org.inharmonia.kakilima.base.domain.User;
import org.inharmonia.kakilima.base.service.PreconditionFailedException;
import org.inharmonia.kakilima.base.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired(required = true)
    private ItemDao itemDao;
    @Autowired(required = true)
    private CategoryDao categoryDao;
    @Autowired(required = true)
    private UserDao userDao;
    @Autowired(required = true)
    private StoreDao storeDao;

    @Override
    public Long addItem(Item item) {
        return itemDao.create(item);
    }

    @Override
    public List<Item> getItemsByCategoryId(Long categoryId){
        Category category = categoryDao.read(categoryId);
        if(category == null){
            throw new PreconditionFailedException("Category with id " + categoryId + " cannot be found");
        }
        return itemDao.getItemsByCategoryId(category);
    }

    @Override
    public List<Store> getStoresByEmail(String email) {
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new PreconditionFailedException("Cannot find user with email: " + email);
        }
        return storeDao.getByUser(user);
    }

    @Override
    public Long addStore(Store store, String email) {
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new PreconditionFailedException("Cannot find user with email: " + email);
        }
        store.setUser(user);
        return storeDao.create(store);
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
