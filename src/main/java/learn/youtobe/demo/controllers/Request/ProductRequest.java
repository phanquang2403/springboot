package learn.youtobe.demo.controllers.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {
    private String name;
    private String fromDate;
    private String toDate;
    private Long quantity;

}
