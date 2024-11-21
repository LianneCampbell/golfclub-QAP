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
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) LocalDate tournamentStartDate
    ) {
        // Filter members using the service layer
        List<Member> allMembers = memberService.getAllMembers();
        List<Member> filteredMembers = allMembers.stream()
                .filter(m -> name == null || m.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(m -> phoneNumber == null || m.getPhoneNumber().contains(phoneNumber))
                .filter(m -> tournamentStartDate == null || m.getTournaments().stream()
                        .anyMatch(t -> t.getStartDate().isEqual(tournamentStartDate)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredMembers);
    }
}