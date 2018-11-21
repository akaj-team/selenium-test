package vn.asiantech.object;

/**
 * @author at-hangtran
 */
public class Employee {
    public String name;
    public String code;
    public String email;
    public String manager;
    public String team;
    public String group;

    public Employee(String name, String code, String email, String manager, String team, String group) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.manager = manager;
        this.team = team;
        this.group = group;
    }
}
