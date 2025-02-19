package main.java.etnities;

public class Queen extends Bee{
    public Queen(String username, String password, int honeyProduced, String role) {
        super(username, password, honeyProduced, role);
    }

    @Override
    public int voteStrength() {
        return 5;
    }
}
