package com.target.casestudy.myretail.repository;

import com.target.casestudy.myretail.entities.ProductPriceEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CassandraRepository<ProductPriceEntity, String> {
}
