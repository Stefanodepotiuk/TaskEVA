package com.example.taskeva.services;

import com.example.taskeva.dao.ProductDAO;
import com.example.taskeva.models.dto.ProductDTO;
import com.example.taskeva.models.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServicesTest {
    @Mock
    ProductDAO productDAO;
    @InjectMocks
    ProductServices productServices;
    ArrayList<Product> products = new ArrayList<>();

    @BeforeEach
    public void prepareData() {
        products = new ArrayList<>();
        products.add(new Product(1L, "EVA", "Good"));
        products.add(new Product(2L, "eva", "Good"));
        products.add(new Product(3L, "Spoon", "Bad"));
        products.add(new Product(4L, "Believable", "Bad"));
    }

    @Test
    void getAll() {
        ArrayList<ProductDTO> expected = new ArrayList<>();
        expected.add(new ProductDTO(1L, "EVA", "Good"));
        expected.add(new ProductDTO(2L, "eva", "Good"));
        expected.add(new ProductDTO(3L, "Spoon", "Bad"));
        expected.add(new ProductDTO(4L, "Believable", "Bad"));

        when(productDAO.findAll()).thenReturn(products);
        List<ProductDTO> result = productServices.getAll();
        assertEquals(expected, result);
    }

    @Test
    void productFilter() {
        ArrayList<ProductDTO> expected = new ArrayList<>();
        expected.add(new ProductDTO(2L, "eva", "Good"));
        expected.add(new ProductDTO(3L, "Spoon", "Bad"));
        expected.add(new ProductDTO(4L, "Believable", "Bad"));

        ArrayList<ProductDTO> expected2 = new ArrayList<>();
        expected2.add(new ProductDTO(1L, "EVA", "Good"));
        expected2.add(new ProductDTO(3L, "Spoon", "Bad"));

        when(productDAO.findAll()).thenReturn(products);

        List<ProductDTO> result = productServices.productFilter("^E.*$");
        List<ProductDTO> result2 = productServices.productFilter("^.*[eva].*$");

        assertEquals(expected, result);
        assertEquals(expected2, result2);
    }
}