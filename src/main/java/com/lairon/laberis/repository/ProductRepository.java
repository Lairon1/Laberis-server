package com.lairon.laberis.repository;

import com.lairon.laberis.domain.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {


}
