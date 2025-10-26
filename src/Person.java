public class Person {
    @JsonField(name = "id")
    private int id;

    @JsonField(name = "full_name")
    private String name;

    @JsonField(name = "active")
    private boolean active;

    @JsonField(name = "role")
    private Role role;

    public enum Role {ADMIN, USER}

    public Person(int id, String name, boolean active, Role role){
        this.id = id;
        this.name = name;
        this.active = active;
        this.role = role;
    }
}
