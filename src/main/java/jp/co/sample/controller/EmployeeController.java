package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * showList追加
     */
    @RequestMapping("/showList")
    public String showList(Model model) {
        employeeService.showList();
        List<Employee> employeeList = employeeService.showList();

        model.addAttribute("employeeList", employeeList);
        return "employee / list";
    }

    /**
     * setUpdateEmployeeForm追加
     */

    @ModelAttribute
    public UpdateEmployeeForm setUpdateEmployeeForm() {
        UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
        return updateEmployeeForm;

    }

    /**
     * showDetail追加
     */
    @RequestMapping("/showDetail")
    public String showDetail(String id, Model model) {
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        return "employee / detail";
    }

    @RequestMapping("/update")
    public String update(UpdateEmployeeForm form) {
        Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
        Employee employ = new Employee();
        employ.setDependentsCount(Integer.parseInt(form.getDependentsCount()));

        employeeService.update(employee);
        return "redirect:/employee/showList";
    }

}
