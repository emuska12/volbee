package main.java.etnities;

public class Drone extends Bee{
    public Drone(String username, String password, int honeyProduced, String role) {
        super(username, password, honeyProduced, role);
    }

    @Override
    public int voteStrength() {
        return 2;
    }
}
