package kz.medet.onlineshop.repository;

import kz.medet.onlineshop.model.Product;
import kz.medet.onlineshop.model.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity , Long> {
    List<ProductQuantity> findAllByProduct_Id(Long productId);

    ProductQuantity findBySellerIdAndProductId(Long sellerId, Long productId);

    List<ProductQuantity> findAllBySeller_Id(Long sellerId);
}
