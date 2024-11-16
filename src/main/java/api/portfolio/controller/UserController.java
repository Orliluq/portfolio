package api.portfolio.controller;

import api.portfolio.dto.UserDTO;
import api.portfolio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId) {
        Optional<UserDTO> userDTO = userService.getUserById(userId);
        return userDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        Optional<UserDTO> updatedUser = userService.updateUser(userId, userDTO);
        return updatedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}