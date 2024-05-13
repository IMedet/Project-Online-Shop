package kz.medet.onlineshop.service;

import kz.medet.onlineshop.dto.OrderDto;
import kz.medet.onlineshop.mapper.OrderMapper;
import kz.medet.onlineshop.model.Order;
import kz.medet.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProductServiceImpl productService;

    public List<OrderDto> getAllOrdersDtoByUserId(Long id){
        return orderMapper.toDtoList(orderRepository.findAllByBuyer_Id(id));
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
    }

    public void saveOrder(Long sellerId, int quantity, Long productId ){
        Order order = new Order();
        order.setStatus("In Process");

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        order.setOrderDate(currentTimestamp);

        order.setBuyer(userService.getCurrentUser());
        order.setSeller(userService.findUserById(sellerId));
        order.setProduct(productService.getProductById(productId));
        order.setQuantity(quantity);

        orderRepository.save(order);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAllOrdersByBuyerId(Long buyerId){
        return orderRepository.findAllByBuyer_Id(buyerId);
    }
}
