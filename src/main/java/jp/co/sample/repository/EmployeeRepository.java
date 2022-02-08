package jp.co.sample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * RowMapperラムダ式定義
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_Mapper = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hireDate"));
        employee.setAddress(rs.getString("mailAddress"));
        employee.setZipCode(rs.getString("zipCode"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependentsCount"));

        return employee;
    };

    /**
     * 従業員情報を入社日で取得
     */
    public Employee findAll(Date hireDate) {
        String sql = "SELECT id,name,image,gender,hireDate,mailAddress,zipCode,address,telephone,salary,characteristics,dependentsCount FROM employees ORDER BY hireDate DESC";
        SqlParameterSource param = new MapSqlParameterSource().addValue("HireDate", hireDate);

        List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_Mapper);

        if (employeeList.size() == 0) {
            return null;
        }
        return employeeList.get(0);

    }

    public Employee load(Integer id) {
        String sql = "SELECT id,name,image,gender,hireDate,mailAddress,zipCode,address,telephone,salary,characteristics,dependentsCount FROM employees WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        try {
            return template.queryForObject(sql, param, EMPLOYEE_ROW_Mapper);
        } catch (Exception e) {
            return null;
        }

    }

    public Employee save(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String updateSql = "UPDATE employees SET name=:name,image=:image,gender=:gender,hireDate=:hireDate,mailAddress=:mailAddress,zipCode=:zipCode,address=:address,telephone=:telephone,salary=:salary,characteristics=:characteristics,dependentsCount=:dependentsCount"
                + "WHERE id=:id";
        template.update(updateSql, param);
        return employee;

    }

}
