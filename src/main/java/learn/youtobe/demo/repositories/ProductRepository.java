package learn.youtobe.demo.repositories;

import learn.youtobe.demo.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{}
