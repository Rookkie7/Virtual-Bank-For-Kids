package entity;

public class Account {
    private String account;
    private String parentCode;

    public Account(String account, String parentCode) {
        this.account = account;
        this.parentCode = parentCode;
    }

    public String getAccount() {
        return account;
    }

    public String getParentCode() {
        return parentCode;
    }
}