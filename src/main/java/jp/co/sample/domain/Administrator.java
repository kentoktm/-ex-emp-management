package jp.co.sample.domain;

/**
 * @author kentonakazawa
 */
public class Administrator {
    /**
     * ID
     */
    private Integer id;
    /**
     * 名前
     */
    private String name;
    /**
     * メルアド
     */
    private String mailAddress;
    /**
     * パスワード
     */
    private String password;

    /**
     * 引数なしのコンストラクタ
     */
    public Administrator() {

    }

    /**
     * すべてのフィールドを引数に取るコンストラクタ
     * 
     */
    public Administrator(Integer id, String name, String mailAddress, String password) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator [id=" + id + ", mailAddress=" + mailAddress + ", name=" + name + ", password=" + password
                + "]";
    }

}
