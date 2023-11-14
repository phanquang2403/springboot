package learn.youtobe.demo.controllers;

import learn.youtobe.demo.Entities.ProductEntity;
import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.controllers.Request.ProductRequest;
import learn.youtobe.demo.repositories.ProductRepository;
import learn.youtobe.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductControllers extends BaseController {


    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        try {
            Date date = new Date();
            Long productSeq = productService.getNextProductIdFromSequence();
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(productSeq);
            productEntity.setName(request.getName());
            productEntity.setQuantity(request.getQuantity());
            productEntity.setCreated(date.getTime());
            return successApi(productService.save(productEntity), "ok");

        } catch (Exception e) {
            log.error("getProduct_error" + e.getMessage());
            return errorApi(e.getLocalizedMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        try {
            return successApi(productRepository.findAll(), "ok");

        } catch (Exception e) {
            log.error("getAllProduct_error " + e.getMessage());
            return errorApi(e.getLocalizedMessage());
        }
    }

}
