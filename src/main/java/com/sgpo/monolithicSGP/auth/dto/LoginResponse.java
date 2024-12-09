package com.sgpo.monolithicSGP.auth.dto;

public record LoginResponse(String accessToken, Long expiresIn ) {
}
