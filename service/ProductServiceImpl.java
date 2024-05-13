package kz.medet.onlineshop.service;

import kz.medet.onlineshop.model.Product;
import kz.medet.onlineshop.model.User;
import kz.medet.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory_Id(Long id){
        return productRepository.findAllByCategory_Id(id);
    }

    public void deleteAllByCategory_Id(Long id){
        productRepository.deleteAllByCategory_Id(id);
    }

    public List<Product> getHighestProducts(){
        return productRepository.findAllByOrderByPriceDesc();
    }

    public void addProduct(String name, String description, Double price, String imageUrl, Long categoryId){
        Product productToFind = productRepository.findByImageUrl(imageUrl);

        if (productToFind == null) {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(categoryService.getByCategoryId(categoryId));
            product.setImageUrl(imageUrl);

            productRepository.save(product);
        }
    }

    public Product getProductByImageUrl(String imageUrl){
        return productRepository.findByImageUrl(imageUrl);
    }
}
