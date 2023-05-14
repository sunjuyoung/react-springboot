package com.example.airbnbApi.review;

import com.example.airbnbApi.listing.Listing;
import com.example.airbnbApi.listing.ListingRepository;
import com.example.airbnbApi.review.dto.RegisterReviewDTO;
import com.example.airbnbApi.review.dto.ResponseReviewDTO;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ListingRepository listingRepository;
    private final UserRepository userRepository;

    public void registerReview(RegisterReviewDTO registerReviewDTO) {
        Listing listing = listingRepository.findOnlyId(registerReviewDTO.getListing_id());
        Account account = userRepository.findOnlyId(registerReviewDTO.getReviewer_id());
        Review review = Review.createReview(listing, account, registerReviewDTO);
        review.setListing(listing);
        reviewRepository.save(review);

    }

    public List<ResponseReviewDTO> getReviewsByListingId(Integer listing_id) {
        List<Review> reviews = reviewRepository.findAllByListingId(listing_id);
        if(reviews != null){
            return reviews.stream().map(review -> new ResponseReviewDTO(review)).collect(Collectors.toList());
        }
        return null;
    }
}
