package learn.youtobe.demo.services.daos;


import learn.youtobe.demo.controllers.request.RoleByUserRequest;
import learn.youtobe.demo.controllers.request.RoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Boolean createRole(RoleRequest request) {
        String sql = "insert into role(role_id, created, create_by, description, role_name, status, updated, update_by) " +
                "values (nextval('role_seq'), now()::TIMESTAMP, :createBy, :description, :roleCode, :status, :updated, :updateBy);";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("createBy", request.getCreateBy());
        map.addValue("description", request.getDescription());
        map.addValue("roleCode", request.getRoleCode());
        map.addValue("status", request.getStatus());
        map.addValue("updated", request.getUpdateBy());
        map.addValue("updateBy", null);

        int rowEffect = jdbcTemplate.update(sql, map);
        return rowEffect > 0;
    }

    public Boolean addRoleUser(RoleByUserRequest request) {
        String sql = " " +
                "INSERT INTO role_user(role_user_id, created, create_by, role_id, updated, update_by, user_id) " +
                "VALUES (nextval('role_user_seq'), now()::TIMESTAMP, :createBy, :roleId, :update, :updateBy, :userId);";
        MapSqlParameterSource map = new MapSqlParameterSource();
        int rowEffect = jdbcTemplate.update(sql, map);
        return rowEffect > 0;
    }
}
