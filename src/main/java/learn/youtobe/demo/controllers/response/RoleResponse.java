package learn.youtobe.demo.controllers.response;


import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
    private Integer moduleId;
    private String moduleCode;
    private String moduleName;
    private String roleName;
    private Integer moduleRoleId;
    private String status;
    private String action;
    private List<String> actions;
    private String activityStatus;
}
