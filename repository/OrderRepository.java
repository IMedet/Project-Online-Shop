package kz.medet.onlineshop.repository;

import kz.medet.onlineshop.dto.OrderDto;
import kz.medet.onlineshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByBuyer_Id(Long buyerId);

}
