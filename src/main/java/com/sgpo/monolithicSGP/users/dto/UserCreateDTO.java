package com.sgpo.monolithicSGP.users.dto;

public record UserCreateDTO(
        String name,
        String email,
        String phone,
        String password
) {
}
