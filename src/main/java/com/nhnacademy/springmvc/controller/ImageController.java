package com.nhnacademy.springmvc.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ImageController {
    @GetMapping("/admin/image/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            Resource classPathResource = new ClassPathResource("/");
            log.debug("classPathResource : {}",classPathResource);
            Path imagePath = Paths.get(classPathResource.getURI()).resolve(fileName);
            log.debug("imagePath : {}",imagePath);
            Resource imageResource = new UrlResource(imagePath.toUri());
            log.debug("imageResource : {}",imageResource);

            return ResponseEntity.ok()
                    .body(imageResource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
