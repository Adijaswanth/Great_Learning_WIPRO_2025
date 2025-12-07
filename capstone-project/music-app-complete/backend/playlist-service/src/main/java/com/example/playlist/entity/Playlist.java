package com.example.playlist.entity;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="playlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String ownerEmail;
  @ElementCollection
  private List<Long> songIds = new ArrayList<>();
}
