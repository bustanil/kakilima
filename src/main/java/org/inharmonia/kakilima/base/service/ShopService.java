package org.inharmonia.kakilima.base.service;

import org.inharmonia.kakilima.base.domain.Item;
import org.inharmonia.kakilima.base.domain.Store;

import java.util.List;

public interface ShopService {

    Long addItem(Item item);

    List<Item> getItemsByCategoryId(Long categoryId);

    List<Store> getStoresByEmail(String email);

    Long addStore(Store store, String email);
}
