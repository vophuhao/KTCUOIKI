package hcmute.com.ShoeShop.repository;

import hcmute.com.ShoeShop.entity.Product;
import hcmute.com.ShoeShop.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    List<ProductDetail> findByProductId(Long productId);
    ProductDetail findByProductAndSize(Product product, int size);

}
