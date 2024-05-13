package kz.medet.onlineshop.service;

import kz.medet.onlineshop.model.Order;
import kz.medet.onlineshop.model.Product;
import kz.medet.onlineshop.model.ProductQuantity;
import kz.medet.onlineshop.repository.ProductQuantityRepository;
import kz.medet.onlineshop.repository.ProductRepository;
import kz.medet.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductQuantityServiceImpl {
    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderServiceImpl orderService;


    public List<ProductQuantity> getProductQuantitiesByProductId(Long productId){
        return productQuantityRepository.findAllByProduct_Id(productId);
    }

    public void decreaseAmountOfProductBySellerId(Long sellerId, Long productId, int amount) {
        ProductQuantity productQuantity = productQuantityRepository.findBySellerIdAndProductId(sellerId, productId);

        if (productQuantity != null) {
            int currentAmount = productQuantity.getQuantity();
            if (currentAmount >= amount) {
                productQuantity.setQuantity(currentAmount - amount);

                productQuantityRepository.save(productQuantity);
            } else {
                throw new RuntimeException("Недостаточно товара для уменьшения");
            }
        } else {
            throw new RuntimeException("Запись не найдена для уменьшения количества товара");
        }
    }

    public void afterDeletedOrder(Long orderId){
        Order order = orderService.getOrderById(orderId);
        ProductQuantity productQuantity = productQuantityRepository.findBySellerIdAndProductId(order.getSeller().getId(), order.getProduct().getId());

        int currentQuantity = productQuantity.getQuantity();
        productQuantity.setQuantity(currentQuantity + order.getQuantity());

        productQuantityRepository.save(productQuantity);
    }

    public List<Product> getMyProducts(){
        List<ProductQuantity> productQuantities = productQuantityRepository.findAllBySeller_Id(userService.getCurrentUser().getId());
        List<Product> products = new ArrayList<>();

        for (ProductQuantity p : productQuantities){
            products.add(p.getProduct());
        }

        return products;
    }

    public void addProduct(int quantity, Product product){
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setProduct(product);
        productQuantity.setSeller(userService.getCurrentUser());
        productQuantity.setQuantity(quantity);

        productQuantityRepository.save(productQuantity);
    }

}
