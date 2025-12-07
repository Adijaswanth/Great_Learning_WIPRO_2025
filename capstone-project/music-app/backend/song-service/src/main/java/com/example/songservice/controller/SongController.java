package com.example.songservice.controller;

import com.example.songservice.entity.Song;
import com.example.songservice.repository.SongRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongRepository repo;
    private final RestTemplate rest = new RestTemplate();


    public SongController(SongRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Song> all() {
        return repo.findAll();
    }

    @PostMapping
    public Song add(@RequestBody Song s) {
        if (s.getReleaseDate() == null) s.setReleaseDate(LocalDate.now());
        Song saved = repo.save(s);          
        try
        { 
        	 rest.postForEntity("http://localhost:8090/api/notify", saved, String.class); 
        }
        catch(Exception e)
        { 
        	System.out.println("Notification failed: " + e.getMessage()); 
        }            
        return saved;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> get(@PathVariable Long id) {
        Optional<Song> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Song> search(@RequestParam String q) {
        return repo.findByNameContainingIgnoreCase(q);
    }
    

    @PutMapping("/{id}/visibility")
    public ResponseEntity<Song> setVisibility(@PathVariable Long id, @RequestParam boolean visible) {
        Optional<Song> o = repo.findById(id);
        if (o.isEmpty()) return ResponseEntity.notFound().build();
        Song s = o.get();
        s.setVisible(visible);
        repo.save(s);
        return ResponseEntity.ok(s);
    }
}
