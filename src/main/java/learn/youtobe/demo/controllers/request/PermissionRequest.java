package learn.youtobe.demo.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest implements Serializable {
    private Integer moduleId;
    private Integer appId; // app gốc
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
