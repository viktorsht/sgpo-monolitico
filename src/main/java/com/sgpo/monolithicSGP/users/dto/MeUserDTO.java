package com.sgpo.monolithicSGP.users.dto;

public record MeUserDTO(String id, String name,
                        String email,
                        String phone, boolean IsAdmin) {
}
