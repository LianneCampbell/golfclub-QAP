package com.keyin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Member addMember(Member member) {
        entityManager.persist(member);
        return member;
    }

    // Get a member by ID
    public Member getMemberById(Long id) {
        return entityManager.find(Member.class, id);
    }

    // Get all members
    public List<Member> getAllMembers() {
        String jpql = "SELECT m FROM Member m";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        return query.getResultList();
    }

    // Search for members
    public List<Member> searchMembers(String name, String phoneNumber, LocalDate tournamentStartDate) {
        String jpql = "SELECT m FROM Member m WHERE " +
                "(:name IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
                "(:phoneNumber IS NULL OR m.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')) AND " +
                "(:tournamentStartDate IS NULL OR EXISTS " +
                "(SELECT t FROM Tournament t JOIN t.members tm WHERE tm.id = m.id AND t.startDate = :tournamentStartDate))";

        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter("name", name);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("tournamentStartDate", tournamentStartDate);

        return query.getResultList();
    }
}