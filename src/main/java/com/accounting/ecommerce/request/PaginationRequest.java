package com.accounting.ecommerce.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequest {
    @Min(0)
    private int page = 0;

    @Min(1)
    @Max(20)
    private int size = 10;
}
