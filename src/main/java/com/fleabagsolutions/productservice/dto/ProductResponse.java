package com.fleabagsolutions.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * used to transfer data between different parts of the application (especially between the client and the server)
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
  private String id;
  private String name;
  private String description;
  private BigDecimal price;
}
