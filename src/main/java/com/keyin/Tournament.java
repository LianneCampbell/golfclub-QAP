package com.keyin;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private Double entryFee;
    private Double cashPrizeAmount;

    @ManyToMany
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> members = new HashSet<>();

    // Getters, Setters, and Constructors
}