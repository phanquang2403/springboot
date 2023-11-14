package learn.youtobe.demo.services;


import learn.youtobe.demo.Entities.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Long getNextProductIdFromSequence();

    ProductEntity save(ProductEntity productEntity) ;
}
