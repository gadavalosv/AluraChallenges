package com.gadv.medvoll.api.domain.model.user;

import jakarta.validation.constraints.NotBlank;

public record UserAutenticationData(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
