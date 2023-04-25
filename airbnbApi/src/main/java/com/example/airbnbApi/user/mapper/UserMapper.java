package com.example.airbnbApi.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface UserMapper {


    Set<Integer> getAccountWithFavoritesById(Integer listing_id);
}
