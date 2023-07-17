package com.bbyra.devops.devopsbbyra;

import com.bbyra.devops.devopsbbyra.models.Product;
import com.bbyra.devops.devopsbbyra.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.util.AssertionErrors.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getNameProduct() {
        Product product = new Product("Chaise_PRODUCT_TEST_CASE", "Une belle chaise", 15.00f, 10, LocalDate.now());
        assertThat(product.getName()).isEqualTo("Chaise_PRODUCT_TEST_CASE");
    }

    @Test
    @Transactional
    void createProduct() throws Exception {
        Product product = new Product("Chaise_PRODUCT_TEST_CASE", "Une belle chaise", 15.00f, 10, LocalDate.now());
        mockMvc.perform(post("/api/products", 42L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isNoContent());

        Optional<Product> createdProduct = productRepository.findProductByName("Chaise");
        if(createdProduct.isEmpty())
            fail("Le produit n'a pas été créé avec succès.");
        assertThat(createdProduct.get().getDetails()).isEqualTo("Une belle chaise");
    }

    @AfterEach
    @Transactional
    void cleanup() {
        productRepository.deleteByName("Chaise_PRODUCT_TEST_CASE");
    }


}
