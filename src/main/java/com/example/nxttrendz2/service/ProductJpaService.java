package com.example.nxttrendz2.service;

import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.repository.ProductJpaRepository;
import com.example.nxttrendz2.repository.CategoryJpaRepository;
import com.example.nxttrendz2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductJpaService implements ProductRepository {
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Override
    public ArrayList<Product> getProducts() {
        List<Product> productList = productJpaRepository.findAll();
        ArrayList<Product> products = new ArrayList<>(productList);
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();
        int id = category.getId();
        try {
            Category Completecategory = categoryJpaRepository.findById(id).get();
            product.setCategory(Completecategory);
            productJpaRepository.save(product);
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Product updateProduct(int productId, Product product) {
        try {
            Product newProduct = productJpaRepository.findById(productId).get();
            if (product.getName() != null) {
                newProduct.setName(product.getName());
            }
            if (product.getPrice() != 0) {
                newProduct.setPrice(product.getPrice());
            }
            if (product.getDescription() != null) {
                newProduct.setDescription(product.getDescription());
            }
            if (product.getCategory() != null) {
                Category category = product.getCategory();
                int id = category.getId();
                Category Completecategory = categoryJpaRepository.findById(id).get();
                newProduct.setCategory(Completecategory);
            }

            productJpaRepository.save(newProduct);
            return newProduct;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productJpaRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Category getCategoryProductById(int productId) {

        try{
            Product product = productJpaRepository.findById(productId).get();
            Category category = product.getCategory();
            int id = category.getId();
            Category Completecategory = categoryJpaRepository.findById(id).get();
            return Completecategory;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}