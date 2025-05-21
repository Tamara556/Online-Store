package com.online.store.endpoint;

import com.online.store.dto.ProductDto;
import com.online.store.dto.SaveProductRequest;
import com.online.store.entity.Product;
import com.online.store.mapper.ProductMapper;
import com.online.store.repository.ProductRepository;
import com.online.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductEndpoint {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(@RequestParam(required = false, defaultValue = "", name = "sort")String sortBy){
        if (!Set.of("name").contains(sortBy)){
            sortBy = "name";
        }
        return productRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody SaveProductRequest saveProductRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var product = Product.builder()
                .name(saveProductRequest.getName())
                .price(saveProductRequest.getPrice())
                .description(saveProductRequest.getDescription())
                .qty(saveProductRequest.getQty())
                .build();

        productRepository.save(product);

        var productDto = productMapper.toDto(product);

        var uri = uriBuilder.path("/products/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).body(productDto);
    }


}
