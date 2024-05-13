package kz.medet.onlineshop.repository;

import kz.medet.onlineshop.model.Product;
import kz.medet.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<User> findSellersByProductId(Long productId);
    List<Product> findAllByCategory_Id(Long id);
    void deleteAllByCategory_Id(Long id);

    List<Product> findAllByOrderByPriceDesc();

    Product findByImageUrl(String imageUrl);
}
