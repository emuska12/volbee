package main.java.election;

import java.util.*;
import java.io.*;
import main.java.etnities.*;
import main.java.gui.*;
import main.java.model.AlreadyCandidateException;
import main.java.model.NotEnoughHoneyException;
import main.java.model.ReachedMaxException;
import main.java.etnities.Bee;

public class ElectionSystem {
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private HashMap<String, Bee> users = new HashMap<>();
    private ArrayList<Voter> votes = new ArrayList<>();
    private HashSet<String> alreadyVoted = new HashSet<>();
    private HashMap<String, Integer> candidateVotes = new HashMap<>();
    private final String pathUsersFile = "src/main/resources/config/users.txt";

    public ElectionSystem() {
        loadUsers();
        candidates.add(new Candidate("Bee1", "Worker", 19));
        candidates.add(new Candidate("Bee2", "Drone", 28));
        candidates.add(new Candidate("Bee3", "Worker", 26));
    }

    private void loadUsers() {
        try (Scanner scanner = new Scanner(new File(pathUsersFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String role = parts[0];
                    String username = parts[1];
                    String password = parts[2];
                    int honey = Integer.parseInt(parts[3]);
                    switch (role) {
                        case "Worker":
                            users.put(username, new Worker(username, password, honey, role));
                            break;
                        case "Drone":
                            users.put(username, new Drone(username, password, honey, role));
                            break;
                        case "Queen":
                            users.put(username, new Queen(username, password, honey, role));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, new file created.");
        }
    }

    private void addUserToFile(Bee bee) {
        try (FileWriter fw = new FileWriter(pathUsersFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             String role = bee instanceof Worker ? "Worker" : bee instanceof Drone ? "Drone" : "Queen";
             String line = role + ";" + bee.getName() + ";" + bee.getPassword() + ";" + bee.getHoneyProduced();
             out.println(line);
        } catch (IOException e) {
            System.out.println("Error, cannot add user: " + e.getMessage());
        }
    }

    public void vote(Bee voter, List<String> preferences) {
        int numPreferences = preferences.size();
        for (int i = 0; i < numPreferences; i++) {
            Candidate candidate = findCandidateByName(preferences.get(i));
            if (candidate != null) {
                int votesToAdd = (numPreferences - i) * voter.voteStrength();
                candidateVotes.put(candidate.getName(), candidateVotes.getOrDefault(candidate.getName(), 0) + votesToAdd);
                if (candidate.getName().equalsIgnoreCase(Session.getUsername())) {
                    Session.setVotesGained(candidateVotes.get(candidate.getName()));
                }
            }
        }
    }


    private Candidate findCandidateByName(String name) {
        for (Candidate candidate : candidates) {
            if (candidate.getName().equalsIgnoreCase(name)) {
                return candidate;
            }
        }
        return null;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> newCandidates) {
        this.candidates = new ArrayList<>(newCandidates);
    }

    public ArrayList<Voter> getVotes() {
        return votes;
    }

    public Bee getUser(String username) {
        return users.get(username);
    }

    public boolean hasUserVoted(String username) {
        return alreadyVoted.contains(username);
    }

    public void markUserAsVoted(String username) {
        alreadyVoted.add(username);
    }

    public Bee getCurrentUser() {
        return users.get(Session.getUsername());
    }

    public boolean isUserCandidate(String username) {
        for (Candidate candidate : candidates) {
            if (candidate.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public int getVotesForCandidate(String username) {
        return candidateVotes.getOrDefault(username, 0);
    }

    public boolean validateCredentials(String username, String password) {
        if (users.containsKey(username) && users.get(username).validatePassword(password)) {
            return true;
        }
        return false;
    }

    public boolean addCandidate(Candidate candidate) throws AlreadyCandidateException, NotEnoughHoneyException, ReachedMaxException {
        if (candidate.getHoneyProduced() < 18) {
            throw new NotEnoughHoneyException("You need to produce at least 18 litres of honey to become a candidate.");
        }

        for (Candidate c : candidates) {
            if (c.getName().equals(candidate.getName())) {
                throw new AlreadyCandidateException("You are already a candidate.");
            }
        }

        if (candidates.size() < 5) {
            candidates.add(candidate);
            return true;
        }
        else {
            throw new ReachedMaxException("Maximum amount of candidates for this election already reached.");
        }
    }

    public void updateCandidateHoney(String username, int newHoney) {
        for (Candidate candidate : candidates) {
            if (candidate.getName().equals(username)) {
                candidate.setHoneyProduced(newHoney);
                break;
            }
        }
    }
    public int signUpUser(String username, String password, String honeyProduced, String role) {
        if (users.containsKey(username)) {
            return 1;
        }
        int honeyNum = Integer.parseInt(honeyProduced);
        Bee newBee = null;
        switch(role) {
            case "Worker":
                newBee = new Worker(username, password, honeyNum, role);
                break;
            case "Drone":
                newBee = new Drone(username, password, honeyNum, role);
                break;
            case "Queen":
                newBee = new Queen(username, password, honeyNum, role);
                break;
        }
        users.put(username, newBee);
        addUserToFile(newBee);
        return 0;
    }
}
