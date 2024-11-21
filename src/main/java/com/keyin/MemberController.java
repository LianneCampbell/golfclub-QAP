package com.keyin;

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
        return ResponseEntity.ok(memberService.searchMembers(name, membershipType, phoneNumber, tournamentStartDate));
    }
}