package com.example.multipledb.controllers;

import com.example.multipledb.models.lyrics.LyricsModel;
import com.example.multipledb.models.request;
import com.example.multipledb.models.song.SongModel;
import com.example.multipledb.repositories.lyrics.LyricsRepo;
import com.example.multipledb.repositories.song.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    LyricsRepo lyricsRepo;
    @Autowired
    SongRepo songRepo;

    @PostMapping
    public ResponseEntity add(@RequestBody request body){
        SongModel songModel = new SongModel();
        LyricsModel lyricsModel = new LyricsModel();
        songModel.setName(body.getSong());
        lyricsModel.setContent(body.getContent());

//        will insert in Song DB
        songRepo.save(songModel);
//        will insert in Lyrics DB
        lyricsRepo.save(lyricsModel);

        return ResponseEntity.ok(body);
    }
}
