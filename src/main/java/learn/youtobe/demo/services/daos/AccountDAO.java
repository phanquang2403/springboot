
package learn.youtobe.demo.services.daos;


import learn.youtobe.demo.controllers.request.AccountsRequest;
import learn.youtobe.demo.controllers.response.AccountResponse;
import learn.youtobe.demo.services.dtos.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountDAO {

    private final NamedParameterJdbcTemplate template;

    public AccountResponse getAllAccount(AccountsRequest request) {
        AccountResponse accountResponse = new AccountResponse();

        String sql = "SELECT * " +
                "FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.updated desc) as idx, " +
                "             user_id                                        userId, " +
                "             username                                       username, " +
                "             phone_number                                   phone, " +
                "             cccd                                           cccd, " +
                "             email                                          email, " +
                "             address                                        address, " +
                "             first_name || ' ' || last_name                 fullname " +
                "      FROM accounts a) a ";

        MapSqlParameterSource map = new MapSqlParameterSource();
        if (request.getPage() != null && request.getPageSize() != null) {
            sql += "where a.idx > :start " +
                    "  and a.idx <= :end";
            int start = (request.getPage() - 1) * request.getPageSize() ;
            int end = request.getPage() * request.getPageSize();
            map.addValue("start", start);
            map.addValue("end", end);

        }

        List<AccountDTO> list = template.query(sql,map, (rs, rowNum) -> AccountDTO.builder()
                .userId(rs.getInt("userId"))
                .username(rs.getString("username"))
                .phone(rs.getString("phone"))
                .cccd(rs.getString("cccd"))
                .email(rs.getString("email"))
                .address(rs.getString("address"))
                .fullname(rs.getString("fullname"))
                .build());
        int totals = countAccount();
        accountResponse.setList(list);
        accountResponse.setTotalRecords(totals);
        return accountResponse;
    }

    public int countAccount() {
        String sql = "select  count(1) from accounts";
        return template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    public  AccountDTO getAccount(Integer userId,String phoneNumber){
         String sql ="SELECT ROW_NUMBER() OVER (ORDER BY a.updated desc) as idx,\n" +
                 "       user_id                                        userId,\n" +
                 "       username                                       username,\n" +
                 "       phone_number                                   phone,\n" +
                 "       cccd                                           cccd,\n" +
                 "       email                                          email,\n" +
                 "       address                                        address,\n" +
                 "       first_name || ' ' || last_name                 fullname\n" +
                 "FROM accounts a\n" +
                 "where a.user_id = :userId\n" +
                 "   OR a.phone_number = :phoneNumber;";
         MapSqlParameterSource map = new MapSqlParameterSource();
         map.addValue("userId",userId);
         map.addValue("phoneNumber",phoneNumber);
         List<AccountDTO> result = template.query(sql,map,new BeanPropertyRowMapper(AccountDTO.class));
         return result.get(0);
    }
}
