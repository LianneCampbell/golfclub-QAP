package com.keyin;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/tournaments"})
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    public TournamentController() {
    }

    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        return ResponseEntity.ok(this.tournamentService.addTournament(tournament));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Tournament> getTournament(@PathVariable Long id) {
        System.out.println("Fetching tournament with ID: " + id);
        return ResponseEntity.ok(this.tournamentService.getTournamentById(id));
    }

    @GetMapping({"/{id}/members"})
    public ResponseEntity<Set<Member>> getTournamentMembers(@PathVariable Long id) {
        System.out.println("Fetching members for tournament with ID: " + id);
        return ResponseEntity.ok(this.tournamentService.getTournamentMembers(id));
    }

    @GetMapping({"/search"})
    public ResponseEntity<List<Tournament>> searchTournaments(@RequestParam(required = false) String location, @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate) {
        System.out.println("Received params - Location: " + location + ", Start Date: " + String.valueOf(startDate));
        List<Tournament> results = this.tournamentService.searchTournaments(location, startDate);
        return ResponseEntity.ok(results);
    }
}
