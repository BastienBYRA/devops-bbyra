package com.bbyra.devops.devopsbbyra.controllers;

import com.bbyra.devops.devopsbbyra.models.Product;
import com.bbyra.devops.devopsbbyra.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        if(product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            productService.createProduct(product);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = {"{productId}"})
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = {"{productId}"})
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String details,
                                                @RequestParam(required = false) Float price,
                                                @RequestParam(required = false) Integer quantity) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            Product product = productService.updateProduct(id, name, details, price, quantity);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
