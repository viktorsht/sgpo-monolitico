package com.sgpo.monolithicSGP.users.controllers;

import com.sgpo.monolithicSGP.routes.entities.TravelRoutes;
import com.sgpo.monolithicSGP.routes.repository.TravelRoutesRepository;
import com.sgpo.monolithicSGP.users.dto.History;
import com.sgpo.monolithicSGP.users.dto.MeUserDTO;
import com.sgpo.monolithicSGP.users.dto.UserCreateDTO;
import com.sgpo.monolithicSGP.users.entities.Role;
import com.sgpo.monolithicSGP.users.entities.TravelHistory;
import com.sgpo.monolithicSGP.users.entities.Users;
import com.sgpo.monolithicSGP.users.mapper.UserMapper;
import com.sgpo.monolithicSGP.users.repositories.RolesRepository;
import com.sgpo.monolithicSGP.users.repositories.TravelHistoryRepository;
import com.sgpo.monolithicSGP.users.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TravelHistoryRepository travelHistoryRepository;
    private final TravelRoutesRepository travelRoutesRepository;

    public UserController(UserRepository userRepository,
                          RolesRepository rolesRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          TravelHistoryRepository travelHistoryRepository,
                          TravelRoutesRepository travelRoutesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.travelHistoryRepository = travelHistoryRepository;
        this.travelRoutesRepository = travelRoutesRepository;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> newUser(@RequestBody UserCreateDTO dto) {
        System.out.println(dto.email());
        var basicRole =  rolesRepository.findByName(Role.Values.Client.name());
        System.out.println(basicRole);

        var userFromDb = userRepository.findByEmail(dto.email());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new Users();
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setPhone(dto.phone());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/me")
    public ResponseEntity<MeUserDTO> getCurrentUser(JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        return user
                .map(users -> ResponseEntity.ok(UserMapper.userToMeUserDTO(users)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build()
                );
    }

    boolean isAdmin(Users user){
        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));
        return scopes.equals("Admin");
    }

    @GetMapping()
    public List<MeUserDTO> getAllUsers() {
        var listUsers =  userRepository.findAll(); // Retorna todos os usuários da tabela
        return listUsers.stream()
                .map(UserMapper::userToMeUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TravelHistory>> getTravelHistory(@PathVariable UUID userId) {
        List<TravelHistory> history = travelHistoryRepository.findByUserId(userId);
        return ResponseEntity.ok(history);
    }

    @PostMapping("/history/{userId}")
    public ResponseEntity<TravelHistory> addTravelToUserHistory(@PathVariable UUID userId,@RequestBody History dto) {

        // Verifique se o usuário existe
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verifique se a rota existe
        TravelRoutes route = travelRoutesRepository.findById(dto.routeId())
                .orElseThrow(() -> new RuntimeException("Travel route not found"));

        // Crie um novo registro no histórico
        TravelHistory history = new TravelHistory();
        history.setUser(user);
        history.setTravelRoute(route);
        history.setTravelDate(dto.travelDate());

        // Salve o registro no banco de dados
        travelHistoryRepository.save(history);

        /*if (route.getSeats() <= 0) {
            throw new RuntimeException("No seats available for this travel route");
        }
        route.setSeats(route.getSeats() - 1);
        travelRoutesRepository.save(route);*/


        return ResponseEntity.ok(history);
    }


}
