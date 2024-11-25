package com.keyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/search")
    public ResponseEntity<List<Tournament>> searchTournaments(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        System.out.println("Received params - Location: " + location + ", Start Date: " + startDate);
        List<Tournament> results = tournamentService.searchTournaments(location, startDate);
        return ResponseEntity.ok(results);
    }
}
