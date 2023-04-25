package com.example.airbnbApi.user;

import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional(readOnly = true)
public interface UserRepositoryExtension {


    Set<Integer> getAccountWithFavoritesById(Integer listing_id);
}
