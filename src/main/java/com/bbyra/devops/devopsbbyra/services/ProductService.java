package com.bbyra.devops.devopsbbyra.services;

import com.bbyra.devops.devopsbbyra.models.Product;
import com.bbyra.devops.devopsbbyra.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        Optional<Product> productsByName = productRepository.findProductByName(product.getName());
        if(productsByName.isPresent())
            throw new IllegalStateException("product with the same name already exist !");
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean productExist = productRepository.existsById(id);
        if (!productExist)
            throw new IllegalStateException("product doesn't exist !");
        productRepository.deleteById(id);
    }

    @Transactional
    public Product updateProduct(Long id, String name, String details, Float price, Integer quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("product doesn't exist !"));

        if(name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
            Optional<Product> productsByName = productRepository.findProductByName(name);
            if(productsByName.isPresent())
                throw new IllegalStateException("product with the same name already exist !");
            product.setName(name);
        }

        if(details != null && details.length() > 0 && !Objects.equals(product.getDetails(), details))
            product.setDetails(details);

        if(price != null && price > 0 && !Objects.equals(product.getPrice(), price))
            product.setPrice(price);

        if(quantity != null && !Objects.equals(product.getQuantity(), quantity))
            product.setQuantity(quantity);

        return product;
    }
}
