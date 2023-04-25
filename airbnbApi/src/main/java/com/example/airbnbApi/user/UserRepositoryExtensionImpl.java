package com.example.airbnbApi.user;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Set;

public class UserRepositoryExtensionImpl extends QuerydslRepositorySupport implements UserRepositoryExtension {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     */
    public UserRepositoryExtensionImpl() {
        super(Account.class);
    }

    @Override
    public Set<Integer> getAccountWithFavoritesById(Integer listing_id) {



        return null;
    }
}
