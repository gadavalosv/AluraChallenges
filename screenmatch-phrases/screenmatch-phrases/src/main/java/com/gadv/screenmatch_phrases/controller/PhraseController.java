package com.gadv.screenmatch_phrases.controller;

import com.gadv.screenmatch_phrases.dto.PhraseDTO;
import com.gadv.screenmatch_phrases.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhraseController {
    @Autowired
    private PhraseService phraseService;
    @GetMapping("/series/frases")
    public PhraseDTO getRandomPhrase(){
        return phraseService.getRandomPhrase();
    }
}
