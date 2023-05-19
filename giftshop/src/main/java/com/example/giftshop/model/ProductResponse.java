package com.example.giftshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class ProductResponse {
    boolean created;
    Long id;

    public static ProductResponse fail() {
        ProductResponse failResponse = new ProductResponse();
        failResponse.setCreated(false);
        return failResponse;
    }

    public static ProductResponse success(Long id) {
        ProductResponse successResponse = new ProductResponse();
        successResponse.setCreated(true);
        successResponse.setId(id);
        return successResponse;
    }
}
