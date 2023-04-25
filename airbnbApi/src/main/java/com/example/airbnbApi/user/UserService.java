package com.example.airbnbApi.user;

import com.example.airbnbApi.user.dto.FavoriteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addFavorite(FavoriteDTO favoriteDTO){
        Account test = userRepository.findByEmail(favoriteDTO.getEmail()).orElseThrow();
        test.getFavorites().add(favoriteDTO.getListing_id());
    }


    public Set<Integer> getFavoriteByEmail(Integer account_id) {
        Account account = userRepository.findFavoritesById(account_id);
        Set<Integer> favorites = account.getFavorites();
        return favorites;
    }
}
