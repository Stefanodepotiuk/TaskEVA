package com.example.taskeva.services;

import com.example.taskeva.dao.ProductDAO;
import com.example.taskeva.models.dto.ProductDTO;
import com.example.taskeva.models.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ProductServices {
    private ProductDAO productDAO;

    public List<ProductDTO> getAll() {
        List<Product> all = productDAO.findAll();
        return all.stream().map(ProductDTO::new).collect(toList());
    }

    public List<ProductDTO> productFilter(String regs) {
        Pattern pattern = Pattern.compile(regs);
        return getAll().stream()
                .filter(productDTO -> !pattern.matcher(productDTO.getName()).find())
                .collect(toList());
    }
}