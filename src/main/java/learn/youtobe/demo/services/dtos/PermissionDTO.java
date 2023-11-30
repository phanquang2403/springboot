package learn.youtobe.demo.services.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    private Integer moduleId;
    private Integer appId;
    private Integer parentId;
    private String moduleCode;
    private String moduleName;
    private Integer docType;
    private Integer docStatus;
    private Long created;
    private Integer createBy;
    private Long updated;
    private Integer updateBy;
    private String url;
}
