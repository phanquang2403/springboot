package learn.youtobe.demo.services.daos;

import learn.youtobe.demo.base.BaseDAO;
import learn.youtobe.demo.controllers.request.InsertUserRequest;
import learn.youtobe.demo.controllers.request.UserRequest;
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
                map.addValue("start",  request.getPage());
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

    public Boolean createAccount(InsertUserRequest request) {
        /*
         * @name seq
         * CREATE SEQUENCE ACCOUNT_SEQ INCREMENT 1 START 1;
         * */

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", request.getUsername());
        map.addValue("email", request.getEmail());
        map.addValue("pass", request.getPass());

        String sql =
                "INSERT INTO account  (id, username, email, password, created_at) VALUES (nextval('ACCOUNT_SEQ'), :username, :email,:pass, now());";

        int result = jdbcTemplate.update(sql, map);
        return result != 0;
    }

    public Boolean isExitsAccount(InsertUserRequest request) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", request.getUsername());
        map.addValue("email", request.getEmail());
        String sql =
                "SELECT count(*) FROM account a WHERE a.username  = :username OR a.email = :email";

        try {
            List<Integer> result = jdbcTemplate.queryForList(sql, map, Integer.class);
//            System.out.println("_____________RESULT__________  " + result);
            return result.get(0) > 0;
        } catch (Exception e) {
            return false;
        }


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
