package com.example.airbnbApi.user;

import com.example.airbnbApi.user.dto.FavoriteDTO;
import com.example.airbnbApi.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/favorite")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteDTO favoriteDTO){
        userService.addFavorite(favoriteDTO);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/favorite/{account_id}")
    public ResponseEntity<?> getFavoriteByEmail(@PathVariable Integer account_id){
        Set<Integer> favorites = userService.getFavoriteByEmail(account_id);
       // Set<Integer> favoritesById = userMapper.getAccountWithFavoritesById(account_id);
        return ResponseEntity.ok().body(favorites);
    }
}
