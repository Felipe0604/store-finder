package com.jumbo.store.finder.repository;

import com.jumbo.store.finder.model.Store;
import java.util.List;

public interface IStoreRepository {
    List<Store> findStores();
}
