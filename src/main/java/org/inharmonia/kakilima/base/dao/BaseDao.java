package org.inharmonia.kakilima.base.dao;

import java.io.Serializable;

public interface BaseDao<T extends Serializable> {

    T read(Long id);

    Long create(T transientObject);

    void update(T transientObject);

    void delete(T persistentObject);
}
