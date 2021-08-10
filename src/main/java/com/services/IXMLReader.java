package com.services;

import com.domain.Product;
import com.domain.Root;

import java.util.List;

public interface IXMLReader {

    void save(Root root);
    List<Product> findProducts(int insureId);
}
