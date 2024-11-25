package com.keyin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class TournamentService {

    @PersistenceContext
    private EntityManager entityManager;

    // Add a new tournament
    @Transactional
    public Tournament addTournament(Tournament tournament) {
        entityManager.persist(tournament);
        return tournament;
    }

    // Get a tournament by ID
    public Tournament getTournamentById(Long id) {
        return entityManager.find(Tournament.class, id);
    }

    // Get all tournaments
    public List<Tournament> getAllTournaments() {
        String jpql = "SELECT t FROM Tournament t";
        TypedQuery<Tournament> query = entityManager.createQuery(jpql, Tournament.class);
        return query.getResultList();
    }

    // Get all members in a specific tournament
    public Set<Member> getTournamentMembers(Long id) {
        Tournament tournament = entityManager.find(Tournament.class, id);
        if (tournament != null) {
            return tournament.getMembers();
        }
        throw new IllegalArgumentException("Tournament not found with ID: " + id);
    }

    // Search tournaments by location, start date, or find all members in a tournament
    public List<Tournament> searchTournaments(String location, LocalDate startDate) {
        String jpql = "SELECT t FROM Tournament t WHERE " +
                "(:location IS NULL OR LOWER(t.location) LIKE LOWER(CONCAT('%', TRIM(:location), '%'))) AND " +
                "(:startDate IS NULL OR t.startDate = :startDate)";

        TypedQuery<Tournament> query = entityManager.createQuery(jpql, Tournament.class);
        query.setParameter("location", location);
        query.setParameter("startDate", startDate);

        return query.getResultList();
    }
}