package com.dulab.master;

import com.dulab.master.service.ProductService;
import com.dulab.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {


    @Autowired
    ProductService productService;

    /**
     * Test for login success.
     */
    @Test
    public void testProductSave() {
        var product = new Product();
        product.setName("1");
        product.setDescription("123456789");
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        var d=80000d;
        product.setPrice(d);
        productService.createProduct(product);
    }
}
