package com.example.multipledb.repositories.song;

import com.example.multipledb.models.song.SongModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends JpaRepository<SongModel, Long> {
}
