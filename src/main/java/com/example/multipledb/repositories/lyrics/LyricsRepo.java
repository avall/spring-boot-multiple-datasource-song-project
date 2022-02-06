package com.example.multipledb.repositories.lyrics;

import com.example.multipledb.models.lyrics.LyricsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LyricsRepo extends JpaRepository<LyricsModel, Long> {
}
