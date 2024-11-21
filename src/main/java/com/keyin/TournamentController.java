package com.keyin;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        return ResponseEntity.ok(tournamentService.addTournament(tournament));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getTournamentById(id));
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<Set<Member>> getTournamentMembers(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getTournamentMembers(id));
    }
}