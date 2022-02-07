package jp.co.sample.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author kentonakazawa
 * 
 */
@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * RowMapperラムダ式定義
     */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_Mapper = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mailAddress"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 管理者情報
     */
    public Administrator insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        if (administrator.getId() == null) {
            String insertSql = "INSERT INTO administrators(name,mailAddress,password)"
                    + "VALUES(:name,:mailAddress,:password)";
            template.update(insertSql, param);
        }

        return administrator;

    }

    /**
     * メルアドとパスワードから管理者情報取得
     */

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT id,name,email,password FROM administrators WHERE mailAddress=:mailAddress and password=:password";

        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
                password);

        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_Mapper);

        if (administratorList.size() == 0) {
            return null;
        }
        return administratorList.get(0);

    }
}
