package kz.medet.onlineshop.service;

import kz.medet.onlineshop.model.Category;
import kz.medet.onlineshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getByCategoryId(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow();
    }
}
