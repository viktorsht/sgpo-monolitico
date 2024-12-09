package com.sgpo.monolithicSGP.users.mapper;

import com.sgpo.monolithicSGP.users.dto.MeUserDTO;
import com.sgpo.monolithicSGP.users.entities.Role;
import com.sgpo.monolithicSGP.users.entities.Users;

import java.util.stream.Collectors;

public class UserMapper {

    static boolean isAdmin(Users user){
        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));
        return scopes.equals("Admin");
    }

    public static MeUserDTO userToMeUserDTO(Users user){
        return new MeUserDTO(
                user.getId().toString(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                isAdmin(user));
    }
}
