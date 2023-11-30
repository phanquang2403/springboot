package learn.youtobe.demo.services.daos;

import learn.youtobe.demo.controllers.request.ModuleActionRequest;
import learn.youtobe.demo.controllers.request.ModuleRoleRequest;
import learn.youtobe.demo.controllers.request.PermissionRequest;
import learn.youtobe.demo.controllers.response.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionDAO {

    private final NamedParameterJdbcTemplate template;

    public boolean createModule(PermissionRequest request) {
        String sql = "INSERT INTO module (id_module, app_id, created, create_by, doc_status, doc_type, module_code, module_name, " +
                "                    parent_id, updated, update_by, url) " +
                "VALUES (nextval('MODULE_SEQ'), " +
                "        :appId, " +
                "        now()::TIMESTAMP, " +
                "        :createBy, " +
                "        :docStatus, " +
                "        :docType, " +
                "        :moduleCode, " +
                "        :moduleName, " +
                "        :parentId, " +
                "        now()::TIMESTAMP, " +
                "        :updateBy, " +
                "        :url);";


        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("appId", request.getAppId());
        map.addValue("createBy", request.getCreateBy());
        map.addValue("docStatus", request.getDocStatus());
        map.addValue("docType", request.getDocType());
        map.addValue("moduleCode", request.getModuleCode());
        map.addValue("moduleName", request.getModuleName());
        map.addValue("parentId", request.getParentId());
        map.addValue("updateBy", request.getCreateBy());
        map.addValue("url", request.getUrl());

        int rowsAffected = template.update(sql, map);
        return rowsAffected > 0;
    }

    public Boolean createModuleRole(ModuleRoleRequest request) {
        String sql = "INSERT INTO module_role(id_module_role, created, create_by, doc_status, doc_type, id_module, role_code, role_name,  " +
                "                        updated, " +
                "                        update_by) " +
                "VALUES (nextval('module_role_seq'), " +
                "        now()::TIMESTAMP, " +
                "        :createBy, " +
                "        :docStatus, " +
                "        :docType, " +
                "        :idModule, " +
                "        :roleCode, " +
                "        :roleName, " +
                "        now()::TIMESTAMP, " +
                "        :updateBy); ";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("createBy", request.getCreateBy());
        map.addValue("docStatus", request.getDocStatus());
        map.addValue("docType", request.getDocType());
        map.addValue("idModule", request.getIdModule());
        map.addValue("roleCode", request.getRoleCode());
        map.addValue("roleName", request.getRoleName());
        map.addValue("updateBy", request.getUpdateBy());

        int rowAffected = template.update(sql, map);
        return rowAffected > 0;
    }

    public Boolean AddRoleAction(ModuleActionRequest request) {
        String sql = "INSERT INTO module_action(id_module_action, action, created, create_by, doc_status, doc_type, id_module_role, updated,  " +
                "                          update_by) " +
                "values (nextval('module_action_seq'), " +
                "        :action, " +
                "        now()::TIMESTAMP, " +
                "        :createBy, " +
                "        :docStatus, " +
                "        :docType, " +
                "        :idModuleRole, " +
                "        now()::TIMESTAMP, " +
                "        :updateBy);";

        List<MapSqlParameterSource> batchParams = new ArrayList<>();
        List<String> listAction = request.getAction();
        if (!listAction.isEmpty()) {
            for (String action : listAction) {
                MapValues(request, batchParams, action);
            }
        }
        int[] rowAffected = template.batchUpdate(sql, batchParams.toArray(new MapSqlParameterSource[0]));
        return Arrays.stream(rowAffected).count() > 0;
    }

    public void MapValues(ModuleActionRequest request, List<MapSqlParameterSource> batchParams, String action) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("action", action);
        map.addValue("createBy", request.getCreateBy());
        map.addValue("docStatus", request.getDocStatus());
        map.addValue("docType", request.getDocType());
        map.addValue("idModuleRole", request.getIdModuleRole());
        map.addValue("updateBy", request.getUpdateBy());
        batchParams.add(map);

    }

    public Boolean ExitsModuleRoleAction(List<String> action, Integer idModuleRole) {
        String sql = "SELECT count(*) FROM module_action WHERE  action in (:action) and id_module_role = :idModuleRole";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("action", action);
        map.addValue("idModuleRole", idModuleRole);
        Integer rowCount = template.queryForObject(sql, map, Integer.class);
        return rowCount > 0;
    }

    public Boolean ExitsModuleRoleCode(String roleCode) {
        String sql = " " +
                "select count(*) from module_role where role_code = :roleCode ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("roleCode", roleCode);
        Integer rowCount = template.queryForObject(sql, map, Integer.class);
        return rowCount > 0;
    }

    public Boolean ExitsModuleCode(String moduleCode) {
        String sql = "select count(m.module_code) from module m where m.module_code = :moduleCode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("moduleCode", moduleCode);
        Integer rowCount = template.queryForObject(sql, map, Integer.class);
        return rowCount > 0;
    }

    public List<RoleResponse> getPermission(Integer appId) {
        String sql = "SELECT m.id_module                                                                moduleId, " +
                "       m.module_code                                                              moduleCode, " +
                "       m.module_name                                                              moduleName, " +
                "       mr.role_name                                                               roleName, " +
                "       ma.id_module_role                                                          moduleRoleId, " +
                "       ma.action                                                                  action, " +
                "       ma.doc_status                                                              status, " +
                "       CASE WHEN ma.doc_status = 1 THEN 'hoạt động' ELSE 'không hoạt động' END AS activityStatus " +
                "FROM module m " +
                "         JOIN module_role mr ON m.id_module = mr.id_module " +
                "         JOIN module_action ma ON mr.id_module_role = ma.id_module_role " +
                "where m.app_id = :appId  ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("appId", appId);
        List<RoleResponse> result = template.query(sql, map, new BeanPropertyRowMapper<>(RoleResponse.class));
        return result;
    }
}
