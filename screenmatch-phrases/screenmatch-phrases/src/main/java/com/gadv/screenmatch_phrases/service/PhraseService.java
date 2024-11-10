package com.gadv.screenmatch_phrases.service;

import com.gadv.screenmatch_phrases.dto.PhraseDTO;
import com.gadv.screenmatch_phrases.model.Phrase;
import com.gadv.screenmatch_phrases.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {
    @Autowired
    private PhraseRepository phraseRepository;

    public PhraseDTO getRandomPhrase() {
        return convertPhraseToPhraseDTO(phraseRepository.getRandomPhrase());
    }

    private PhraseDTO convertPhraseToPhraseDTO(Phrase randomPhrase) {
        return new PhraseDTO(
                randomPhrase.getId(),
                randomPhrase.getTitle(),
                randomPhrase.getCharacter(),
                randomPhrase.getPosterURL(),
                randomPhrase.getPhrase()
        );
    }
}
