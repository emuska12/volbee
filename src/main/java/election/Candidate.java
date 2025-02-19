package main.java.election;

public class Candidate {
    private String username;
    private String role;
    private int honeyProduced;

    public Candidate(String username, String role, int honeyProduced) {
        this.username = username;
        this.role = role;
        this.honeyProduced = honeyProduced;
    }

    public String getName() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public int getHoneyProduced() {
        return honeyProduced;
    }

    public void setHoneyProduced(int honey) {
        this.honeyProduced = honey;
    }
}
