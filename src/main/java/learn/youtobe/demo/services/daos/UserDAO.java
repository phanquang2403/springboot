package learn.youtobe.demo.services.daos;

import learn.youtobe.demo.base.BaseDAO;
import learn.youtobe.demo.controllers.Request.InsertUserRequest;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDAO extends BaseDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserResponse getListAccount(UserRequest request) {

        try {
            UserResponse userResponse = new UserResponse();
            MapSqlParameterSource map = new MapSqlParameterSource();

            if (request.getPage() != null) {
                map.addValue("start", (request.getPage() - 1) * request.getPageSize() + 1);
            } else {
                map.addValue("start", 1);
            }

            if (request.getPageSize() != null && request.getPage() != null) {
                map.addValue("end", request.getPageSize() * request.getPage());
            } else {
                map.addValue("end", 10);

            }

            String moreQuery = "";
            if (request.getName() != null && !request.getName().isEmpty()) {
                String temp1 = "and  username = '" + request.getName().toLowerCase() + "'";
                moreQuery += temp1;
            }

            String sql = "select   subquery.email as email ,subquery.username as name" +
                    " FROM (" +
                    " SELECT a.email,a.username, ROW_NUMBER() OVER (ORDER BY a.username asc) as seqnum FROM account a where  1=1 " + moreQuery +
                    " ) AS subquery" +
                    " where seqnum BETWEEN :start AND :end";

            List<UserDTO> list = jdbcTemplate.query(
                    sql.toLowerCase(), map,
                    (rs, rowNum) -> UserDTO.builder()
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .build());


            userResponse.setList(list);
            userResponse.setTotalRecords(countAllDocStatus(request));
            return userResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserResponse createAccount(InsertUserRequest request) {
        /*
         * @name seq
         * CREATE SEQUENCE ACCOUNT_SEQ INCREMENT 1 START 1;
         * */

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", request.getUsername());
        map.addValue("email", request.getUsername());
        map.addValue("pass", request.getPass());

        String sql =
                "INSERT INTO account  (id, username, email, password, created_at) VALUES (nextval('ACCOUNT_SEQ'), :username, :email,:pass, now());";

        jdbcTemplate.update(sql, map);

        return null;
    }

    public Boolean isExitsAccount(InsertUserRequest request) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", request.getUsername());
        String sql = "select a.username  from account a where a.username = ':username'";
        List<Integer> countAccount = jdbcTemplate.queryForList(sql, map, Integer.class);
        return countAccount.isEmpty();

    }

    public Integer countAllDocStatus(UserRequest request) {
        String moreQuery = "";
        if (request.getName() != null && !request.getName().isEmpty()) {
            String temp1 = "where  username = '" + request.getName().toLowerCase() + "'";
            moreQuery += temp1;
        }

        String sql = "select   count(a.username) as username from account a " + moreQuery;

        List<Integer> result = jdbcTemplate.query(sql.toLowerCase(), (rs, rowNum) -> rs.getInt("username"));
        return result.isEmpty() ? null : result.get(0).intValue();

    }
}
