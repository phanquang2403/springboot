package learn.youtobe.demo.services.daos;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDAO {

    private  final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
        public Long getSeq(){
            String sql = "SELECT nextval('product_seq') ";
            return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Long.class);

        }

}
