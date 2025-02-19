package main.java.election;

import main.java.etnities.Bee;

public class Session {
    private static String username;
    private static String role;
    private static int honeyProduced;
    private static boolean hasVoted;
    private static boolean isCandidate;
    private static int votesGained;

    public static void setUserDetails(String username, String role, int honeyProduced,
                                      boolean hasVoted, boolean isCandidate, int votesGained) {
        Session.username = username;
        Session.role = role;
        Session.honeyProduced = honeyProduced;
        Session.hasVoted = hasVoted;
        Session.isCandidate = isCandidate;
        Session.votesGained = votesGained;
    }


    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static int getHoneyProduced() {
        return honeyProduced;
    }

    public static boolean hasVoted() {
        return hasVoted;
    }

    public static boolean isCandidate() {
        return isCandidate;
    }

    public static int getVotesGained() {
        return votesGained;
    }

    // Additional methods to update individual attributes as needed
    // Example:
    public static void addHoneyProduced(int honey) {
        honeyProduced += honey;
    }

    public static void setIsCandidate(boolean candidate) {
        Session.isCandidate = candidate;
    }

    public static void setHasVoted(boolean voted) {
        Session.hasVoted = voted;
    }

    public static void setVotesGained(int votes) {
        Session.votesGained = votes;
    }
}
