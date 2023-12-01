package learn.youtobe.demo.controllers.request;


import lombok.Data;

@Data
public class RoleRequest {
    private String roleCode;
    private String roleName;
    private String description;
    private Integer status;
    private Integer createBy;
    private Integer updateBy;
}
