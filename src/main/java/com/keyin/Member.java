package com.keyin;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate startDate;
    private int durationInMonths; // Membership duration

    @ManyToMany(mappedBy = "members")
    private Set<Tournament> tournaments = new HashSet<>();

    // Getters, Setters, and Constructors
}