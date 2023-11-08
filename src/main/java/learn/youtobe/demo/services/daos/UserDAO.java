package learn.youtobe.demo.services.daos;

import learn.youtobe.demo.base.BaseDAO;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDAO extends BaseDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public UserResponse getListAccount(UserRequest request){
        UserResponse userResponse  = new UserResponse();
        try {
            String moreQuery = "";
            if(request.getName() != null && !request.getName().equals("")){
                String temp1 = "where  username = '" + request.getName().toLowerCase() + "'";
                moreQuery += temp1;
            }

            String sql ="select  a.username as name, a.email  as email from account a "+ moreQuery;
            System.out.println("sql" +sql);
            List<UserDTO> list = jdbcTemplate.query(
                    sql,
                    (rs, rowNum) -> UserDTO.builder()
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .build());
            userResponse.setList(list);
            userResponse.setTotalRecords(countAllDocStatus(request));
            System.out.println("userResponse = " + userResponse);
            return  userResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer countAllDocStatus(UserRequest request) {
        String moreQuery = "";
        if(request.getName() != null && !request.getName().equals("")){
            String temp1 = "where  username = '" + request.getName().toLowerCase() + "'";
            moreQuery += temp1;
        }

        String sql ="select   count(a.username) as username from account a "+ moreQuery;

        List<Integer> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("username"));
        return result.isEmpty() ? null : result.get(0).intValue();

    }
}
