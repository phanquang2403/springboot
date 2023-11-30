package learn.youtobe.demo.controllers.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRoleRequest {
    private  Integer createBy;
    private Integer docStatus;
    private Integer docType;
    private Integer idModule;
    private String roleCode;
    private String roleName;
    private Integer updateBy;
}
