package com.example.giftshop;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;
import com.example.giftshop.model.ProductResponse;
import com.example.giftshop.model.ProductUpdateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ProductResponse createProduct(@RequestBody ProductCommand productCommand) {
        productService.createProduct(productCommand);
        return getProductResponse(productCommand.title());
    }
    @PatchMapping("/product")
    public void updatePrice(@RequestBody ProductUpdateCommand productUpdateCommand) {
        productService.updateProduct(productUpdateCommand);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false, defaultValue = "") String searchQuery
    ) {
        return ResponseEntity.ok(productService.getProducts(searchQuery));
    }

    public ProductResponse getProductResponse(String title) {
        return productService.getProductByTitle(title)
                .map(x -> ProductResponse.success(x.getId()))
                .orElse(ProductResponse.fail());
    }
}
