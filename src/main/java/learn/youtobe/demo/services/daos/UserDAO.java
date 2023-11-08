package learn.youtobe.demo.services.daos;

import learn.youtobe.demo.base.BaseDAO;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.dtos.UserDTO;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class UserDAO extends BaseDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    public UserResponse getListAccount(UserRequest request){
        UserResponse userResponse  = new UserResponse();
        try {
            String moreQuery = "";
            if(request.getName() != null && !request.getName().equals("")){
                String temp1 = "AND LOWER( fds.NAME) LIKE '%" + request.getName().toLowerCase() +"%'";
                moreQuery += temp1;
            }

            String sql ="SELECT fds.name AS name,fds.email AS email FROM ERP_AC.FICO_DOCSTATUS fds where fds.ISACTIVE != 'D' "+ moreQuery;
            List<UserDTO> list = jdbcTemplate.query(
                    sql,
                    (rs, rowNum) -> UserDTO.builder()
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .build());
            userResponse.setList(list);
            userResponse.setTotalRecords(countAllDocStatus(request));
            return  userResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer countAllDocStatus(UserRequest request) {
        String moreQuery = "";
        if(request.getName() != null && !request.getName().equals("")){
            String temp1 = "AND LOWER( fds.NAME) LIKE '%" + request.getName().toLowerCase() +"%'";
            moreQuery += temp1;
        }

        String sql ="SELECT count(fds.DOCS_ID) docsId FROM ERP_AC.FICO_DOCSTATUS fds where fds.ISACTIVE != 'D' "+ moreQuery;

        List<Integer> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("docsId"));
        return result.isEmpty() ? null : result.get(0).intValue();

    }
}
