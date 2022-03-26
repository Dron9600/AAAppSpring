package kz.prudnikov.airAstanaAppSpring.getterSetter;

public class Data {

    private long id;
    private String content;
    private Integer customer;

    public long getId() {return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }
}
