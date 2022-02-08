package jp.co.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報全件取得
     * 
     * @return
     */
    public List<Employee> showList() {
        employeeRepository.findAll();
        return new ArrayList<Employee>();

    }

    /**
     * showDetail追加
     */
    public Employee showDetail(Integer id) {
        employeeRepository.load(id);

        return employeeRepository.load(id);
    }

}
