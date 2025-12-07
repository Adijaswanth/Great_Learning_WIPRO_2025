package com.example.playlist.controller;

import com.example.playlist.entity.Playlist;
import com.example.playlist.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // ⚠️ New Import
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistRepository repo;

    @Autowired
    private RestTemplate restTemplate; // 1. Inject RestTemplate

    public PlaylistController(PlaylistRepository repo){ 
        this.repo = repo; 
    }
    
    // -----------------------------------------------------------
    // ✅ NEW METHOD: Fetch Playlist Content with Full Song Details
    // -----------------------------------------------------------
    @GetMapping("/{id}/details")
    public ResponseEntity<List<Object>> getPlaylistDetails(@PathVariable Long id) {
        Optional<Playlist> o = repo.findById(id);
        if (o.isEmpty()) return ResponseEntity.notFound().build();

        Playlist playlist = o.get();
        List<Object> songDetails = new ArrayList<>();

        // 2. Loop through Song IDs and call Song Service for each one
        for (Long songId : playlist.getSongIds()) {
            try {
                // Call Song Service using its Eureka service name
                String songServiceUrl = "http://SONG-SERVICE/api/songs/" + songId;
                
                // Fetch the Song object (as an untyped Map/Object)
                ResponseEntity<Object> response = restTemplate.exchange(
                        songServiceUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Object>() {}
                );

                if (response.getStatusCode().is2xxSuccessful()) {
                    songDetails.add(response.getBody());
                }
            } catch (Exception e) {
                // If one song fails (e.g., deleted), log error and continue
                System.err.println("Failed to fetch song ID " + songId + " for playlist " + id);
            }
        }

        return ResponseEntity.ok(songDetails);
    }
    
    // -----------------------------------------------------------
    // (Existing CRUD methods are below)
    // -----------------------------------------------------------

    @GetMapping
    public List<Playlist> all(){ return repo.findAll(); }
    
    @GetMapping("/owner/{email}")
    public List<Playlist> byOwner(@PathVariable String email){ return repo.findByOwnerEmail(email); }
    
    @PostMapping
    public Playlist create(@RequestBody Playlist p){ return repo.save(p); }
    
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> get(@PathVariable Long id){
        Optional<Playlist> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> update(@PathVariable Long id, @RequestBody Playlist p){
        Optional<Playlist> o = repo.findById(id);
        if(o.isEmpty()) return ResponseEntity.notFound().build();
        Playlist exist = o.get();
        exist.setName(p.getName());
        exist.setSongIds(p.getSongIds());
        repo.save(exist);
        return ResponseEntity.ok(exist);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ repo.deleteById(id); }
    
    @PostMapping("/{id}/songs/{songId}")
    public ResponseEntity<Playlist> addSong(@PathVariable Long id, @PathVariable Long songId){
        Optional<Playlist> o = repo.findById(id);
        if(o.isEmpty()) return ResponseEntity.notFound().build();
        Playlist p = o.get();
        p.getSongIds().add(songId);
        repo.save(p);
        return ResponseEntity.ok(p);
    }
    
    @DeleteMapping("/{id}/songs/{songId}")
    public ResponseEntity<Playlist> removeSong(@PathVariable Long id, @PathVariable Long songId){
        Optional<Playlist> o = repo.findById(id);
        if(o.isEmpty()) return ResponseEntity.notFound().build();
        Playlist p = o.get();
        p.getSongIds().remove(songId);
        repo.save(p);
        return ResponseEntity.ok(p);
    }
}