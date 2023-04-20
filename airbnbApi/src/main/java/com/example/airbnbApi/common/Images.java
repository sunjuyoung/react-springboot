package com.example.airbnbApi.common;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class Images{

    String fileName;
    String uuid;

    public Images(String fileName, String uuid) {
        this.fileName = fileName;
        this.uuid = uuid;
    }

    public String link(){
        return  uuid+"_"+fileName;
    }


}
