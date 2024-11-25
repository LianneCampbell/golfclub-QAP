package com.keyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.addMember(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String membershipType,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) LocalDate tournamentStartDate
    ) {
        // Log parameters to debug
        System.out.println("Name: " + name);
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Tournament Start Date: " + tournamentStartDate);

        List<Member> filteredMembers = memberService.searchMembers(name, membershipType, phoneNumber, tournamentStartDate);
        return ResponseEntity.ok(filteredMembers);
    }

    @PutMapping("/{id}/membershipType")
    public ResponseEntity<Member> updateMembershipType(
            @PathVariable Long id,
            @RequestParam String membershipType) {
        Member updatedMember = memberService.updateMembershipType(id, membershipType);
        return ResponseEntity.ok(updatedMember);
    }

}