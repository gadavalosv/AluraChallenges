package com.gadv.screenmatch_phrases.dto;

public record PhraseDTO(
        Long id,
        String title,
        String character,
        String posterURL,
        String phrase
) {
}
