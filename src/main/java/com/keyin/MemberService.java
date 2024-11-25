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

    @Transactional
    public Member updateMembershipType(Long id, String membershipType) {
        Member member = entityManager.find(Member.class, id);
        if (member == null) {
            throw new IllegalArgumentException("Member not found with ID: " + id);
        }
        member.setMembershipType(membershipType);
        return member;
    }

    public List<Member> searchMembers(String name, String membershipType, String phoneNumber, LocalDate tournamentStartDate) {
        System.out.println("Searching with params: ");
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Tournament Start Date: " + tournamentStartDate);

        String jpql = "SELECT m FROM Member m WHERE " +
                "(:name IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
                "(:phoneNumber IS NULL OR LOWER(m.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%'))) AND " +
                "(:membershipType IS NULL OR LOWER(m.membershipType) LIKE LOWER(CONCAT('%', :membershipType, '%'))) AND " +
                "(:tournamentStartDate IS NULL OR EXISTS " +
                "(SELECT t FROM Tournament t JOIN t.members tm WHERE tm.id = m.id AND t.startDate = :tournamentStartDate))";

        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter("name", name);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("membershipType", membershipType);
        query.setParameter("tournamentStartDate", tournamentStartDate);

        return query.getResultList();
    }
}