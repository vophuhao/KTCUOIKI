package hcmute.com.ShoeShop.repository;

import hcmute.com.ShoeShop.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
        List<OrderDetail> findOrderDetailsByOrderId(int orderId);
}
