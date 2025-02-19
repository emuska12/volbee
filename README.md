showcase video: https://youtu.be/ApXHhhR_mbI
# 🐝 VOLBEE – Election Simulation in a Beehive
# Project Overview
VOLBEE is a Java-based application that simulates the election process within a beehive using object-oriented programming (OOP) principles. The project creates an interactive and engaging environment where different types of bees (Workers, Drones, and the Queen) can log in, participate in elections, produce honey, and monitor real-time voting results.

The system supports multiple voting methods, dynamic candidate management, and a visually appealing graphical user interface (GUI) built with JavaFX.

# Features
- User Authentication – Bees log in or register to access the system.
- Role-Based Access – Bees have different roles (Worker, Drone, Queen) with specific privileges.
- Candidate System – Bees can view candidates for the queen election and apply if they meet the conditions.
- Voting System – Elections are conducted via preferential or simple voting methods.
- Live Election Monitoring – View real-time election status and announcements.
- Mini-Game: Honey Production – Users can produce honey by clicking flowers in a small interactive game.
- Graphical Representation of Results – Election outcomes are displayed visually through graphs.
- Admin Panel – The administrator manages and controls the election process.

# Technologies Used
Java – Core logic implementation.
JavaFX – Graphical User Interface (GUI).
FXML – Scene building and UI structuring.
OOP Principles – Encapsulation, Inheritance, Polymorphism, and Abstraction.
Custom Exception Handling – Ensures robust error management.
# System Architecture
The system is structured into multiple modules for better organization:
- entities – User and bee-related classes (Hive, Bee, Worker, Drone, Queen).
- election – Voting logic and election management (ElectionSystem, Candidate, BotVoter, VotingStrategy).
- gui – Controllers and user interface (ActualitiesController, AdminLoginController, AdminStatusController).
- util – Exceptions and helper classes (HiveException, Session, MessageManager, WindowSetUtility).
- resources – User data files, FXML scenes, images.

# OOP Concepts Applied
Encapsulation – Data hiding with protected/private attributes in Bee and its subclasses.
Inheritance – Worker, Drone, and Queen extend from the abstract Bee class.
Polymorphism – Different vote strengths for each bee type using method overriding (voteStrength()).
Aggregation – ElectionSystem maintains candidates without owning them.
Custom Exceptions – HiveException parent class with specific exceptions (AlreadyCandidateException, NotEnoughHoneyException, ReachedMaxException).
Strategy Pattern – VotingStrategy interface supports multiple voting methods.
Factory Pattern – Hive.createBee() dynamically creates bee instances.
Multithreading – BotVoter thread runs automatic voting in the background.
# Interactive Gameplay
Produce honey while logged in by clicking flowers.
Participate in dynamic elections with real-time voting updates.
Customize voting experience with two different election methods.
# Future Enhancements
Additional bee types with unique behaviors.
More interactive mini-games for honey production.
Expanded administrator functionalities.
This project was an exciting implementation of OOP concepts in a real-world simulation.
