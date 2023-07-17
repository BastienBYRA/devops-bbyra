package com.bbyra.devops.devopsbbyra.repository;

import com.bbyra.devops.devopsbbyra.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name = ?1")
    Optional<Product> findProductByName(String productName);

    void deleteByName(String name);
}
