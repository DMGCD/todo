package TM;

public class todoTM {
    private String id;
    private String description;
    private String userid;

    public todoTM() {
    }

    public todoTM(String id, String description, String userid) {
        this.id = id;
        this.description = description;
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return description;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
