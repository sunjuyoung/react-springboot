package com.example.airbnbApi;

import com.example.airbnbApi.common.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class DemoController {

    private final PhotoService photoService;


    @Value("${airbnb.upload.path}")
    private String uploadPath;




    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        photoService.uploadImage(file);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] img = photoService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(img);
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(uploadDTO uploadDTO){

        if(uploadDTO.files() != null){
            List<uploadResponse> list = new ArrayList<>();
            uploadDTO.files().forEach(multiFile->{
                String originalName = multiFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

                try {
                    multiFile.transferTo(savePath);

                }catch (IOException e){

                }
                list.add(new uploadResponse(uuid,originalName));

            });
            return ResponseEntity.ok().body(list);
        }

        return null;
    }


    record uploadDTO(List<MultipartFile> files){
    }

    record uploadResponse(
            String uuid,
            String fileName
    ){


        public String getLink(){
            return uuid+"_"+fileName;
        }

    }


}