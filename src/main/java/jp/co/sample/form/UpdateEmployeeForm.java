package jp.co.sample.form;

public class UpdateEmployeeForm {

    /**
     * 従業員id
     */
    private String id;

    /**
     * 扶養人数
     */
    private String dependentsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeForm [dependentsCount=" + dependentsCount + ", id=" + id + "]";
    }

}
