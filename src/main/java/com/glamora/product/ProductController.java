package com.glamora.product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Product> all(@RequestParam(required = false) String category) {
        return (category == null || category.isBlank())
                ? repository.findAll()
                : repository.findByCategoryIgnoreCase(category);
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product) { return repository.save(product); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product input) {
        Product product = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        product.setName(input.getName()); product.setCategory(input.getCategory()); product.setPrice(input.getPrice());
        product.setDescription(input.getDescription()); product.setImageUrl(input.getImageUrl());
        product.setBadge(input.getBadge()); product.setStock(input.getStock());
        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        repository.deleteById(id);
    }
}
