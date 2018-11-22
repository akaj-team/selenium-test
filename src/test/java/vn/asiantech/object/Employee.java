package vn.asiantech.object;

/**
 * @author at-hangtran
 */
public class Employee {
    private String name;
    private String code;
    private String email;
    private String manager;
    private String team;
    private String group;

    public Employee(String name, String code, String email, String manager, String team, String group) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.manager = manager;
        this.team = team;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getManager() {
        return manager;
    }

    public String getTeam() {
        return team;
    }

    public String getGroup() {
        return group;
    }
}
