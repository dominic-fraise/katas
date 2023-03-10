package com.example.giftshop;

import com.example.giftshop.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product) {
        //why do we need to send the ID?

         productService.createProduct(product);
    }
}
