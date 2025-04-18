package model.domain;

public enum Role {
    LOGIN(0),
    PERSONAL(1),
    ATLETA(2),
    GESTORE(3);

    private final int id;

    private Role(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static Role fromInt(int id){
        for (Role role: Role.values()){
            if(role.getId() == id) return role;
        }

        return null;
    }
}
