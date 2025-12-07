package com.example.playlist.repository;
import com.example.playlist.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
  List<Playlist> findByOwnerEmail(String ownerEmail);
}
