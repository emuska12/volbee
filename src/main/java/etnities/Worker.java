package main.java.etnities;

public class Worker extends Bee{
    public Worker(String username, String password, int honeyProduced, String role) {
        super(username, password, honeyProduced, role);
    }

    @Override
    public int voteStrength() {
        return 1;
    }
}

