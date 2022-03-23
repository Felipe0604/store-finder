package com.jumbo.store.finder.repository;

import java.io.Serializable;

/**
 * Common interface for a File Reader Repository.
 *
 * @author Felipe Gonzalez
 */
public interface FileStoreRepository<T extends Serializable> {

    Iterable<T> findAll();

}
