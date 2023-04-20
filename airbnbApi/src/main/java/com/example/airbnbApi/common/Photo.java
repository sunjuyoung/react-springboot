package com.example.airbnbApi.common;

import com.example.airbnbApi.listing.Listing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Photo {

    @Id
    private String uuid;

    @Column(nullable = false)
    private String fileName;

    private String fileType;

//
//    @Lob
//    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;





    public void setPhoto(Listing listing){
        this.listing = listing;
        listing.getImages().add(this);
    }


}
