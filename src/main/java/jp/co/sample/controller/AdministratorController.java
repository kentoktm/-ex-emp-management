package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    /**
     * session宣言
     */
    @Autowired
    private HttpSession session;

    /**
     * InsertAdministratorFormインスタンス化
     */
    @ModelAttribute
    public InsertAdministratorForm setUpInsertAdministratorForm() {
        return new InsertAdministratorForm();
    }

    /**
     * administrator/insert.htmlにフォワード
     */
    @RequestMapping("/toInsert")
    public String toInsert() {
        return "administrator/insert";
    }

    /**
     * 管理者情報登録
     */
    @RequestMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());
        administratorService.insert(administrator);
        return "redirect:/administer/insert";
    }

    /**
     * LoginFormをインスタンス化しreturn
     */
    @ModelAttribute
    public LoginForm setUpLoginForm() {
        return new LoginForm();
    }

    /**
     * administrator/login.htmlへフォワード
     */
    @RequestMapping("/")
    public String toLogin() {
        return "administrator/login.html";
    }

    /**
     * loginメソッド
     */
    @RequestMapping("/login")
    public String login(LoginForm form, Model model) {
        administratorService.login(form.getMailAddress(), form.getPassword());
        AdministratorService administratorService = new AdministratorService();

        if (administratorService.equals(null)) {
            model.addAttribute("error", "メールアドレスまたはパスワードが不正です");
        } else {
            session.setAttribute("name", administratorService.getName());
        }
        return "employee/showList";

    }

}
