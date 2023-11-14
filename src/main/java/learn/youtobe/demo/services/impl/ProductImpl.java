package learn.youtobe.demo.services.impl;

import jakarta.transaction.Transactional;
import learn.youtobe.demo.Entities.ProductEntity;
import learn.youtobe.demo.repositories.ProductRepository;
import learn.youtobe.demo.services.ProductService;
import learn.youtobe.demo.services.daos.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {

    private final ProductDAO productDAO;
    private final ProductRepository productRepository;

    @Override
    public Long getNextProductIdFromSequence() {
        return productDAO.getSeq();
    }

    @Override
    @Transactional()
    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }
}
