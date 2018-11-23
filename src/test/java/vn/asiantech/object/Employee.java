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

    public Employee(final String name, final String code, final String email, final String manager, final String team, final String group) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.manager = manager;
        this.team = team;
        this.group = group;
    }

    public final String getName() {
        return name;
    }

    public final String getCode() {
        return code;
    }

    public final String getEmail() {
        return email;
    }

    public final String getManager() {
        return manager;
    }

    public final String getTeam() {
        return team;
    }

    public final String getGroup() {
        return group;
    }
}
