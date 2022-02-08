package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository administerRepository;

    /**
     * insertメソッド追加
     * 管理者情報を挿入
     */
    public void insert(Administrator administrator) {
        administerRepository.insert(administrator);
    }

    /**
     * ログイン処理
     */
    public String login(String mailAddress, String password) {
        administerRepository.findByMailAddressAndPassword(mailAddress, password);
        return "Administrator";

    }

    public Object getName() {
        return null;
    }

}
