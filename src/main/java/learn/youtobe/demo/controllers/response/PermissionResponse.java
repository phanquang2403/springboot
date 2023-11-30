package learn.youtobe.demo.controllers.response;

import learn.youtobe.demo.services.dtos.PermissionDTO;
import lombok.Data;

import java.util.List;


@Data
public class PermissionResponse {
    private List<PermissionDTO> list;
    private Integer totalRecords;

}
