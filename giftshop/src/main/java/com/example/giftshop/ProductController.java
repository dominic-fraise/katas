package com.example.giftshop;

import com.example.giftshop.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
public class ProductController {

    @PostMapping("/product")
    public String createProduct() {
        return "hello";
    }
}
