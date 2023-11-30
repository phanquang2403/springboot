package learn.youtobe.demo.controllers.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleActionRequest {
    private List<String> action;
    private Integer createBy;
    private Integer docStatus;
    private Integer docType;
    private Integer idModuleRole;
    private Integer updateBy;
}
