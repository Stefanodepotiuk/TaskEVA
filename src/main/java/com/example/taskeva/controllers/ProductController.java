package com.example.taskeva.controllers;

import com.example.taskeva.models.dto.ProductDTO;
import com.example.taskeva.services.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class ProductController {
    ProductServices productServices;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        if (productServices.getAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getFilterNameProducts(@RequestParam("nameFilter") String regs) {
        if (productServices.productFilter(regs).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productServices.productFilter(regs), HttpStatus.OK);
    }
}
