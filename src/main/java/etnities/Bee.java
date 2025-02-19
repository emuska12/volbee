package main.java.etnities;

public abstract class Bee {
    protected String username;
    protected String password;
    protected int honeyProduced;
    protected String role;

    public Bee(String username, String password, int honeyProduced, String role) {
        this.username = username;
        this.password = password;
        this.honeyProduced = honeyProduced;
        this.role = role;
    }

    public String getName() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public boolean validatePassword(String passwordInput) {
        return this.password.equals(passwordInput);
    }

    public int getHoneyProduced() {
        return honeyProduced;
    }

    public String getRole() {
        return role;
    }


    public abstract int voteStrength();
}
