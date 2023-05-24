package com.lairon.laberis.controller;

import com.lairon.laberis.domain.Product;
import com.lairon.laberis.domain.User;
import com.lairon.laberis.domain.UserRole;
import com.lairon.laberis.repository.ProductRepository;
import com.lairon.laberis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(path = "/image")
public class ImageController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private File imagesDir = new File("images" + File.separator);

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestBody MultipartFile file, @RequestParam long id, @RequestParam String token) throws IOException {
        User user = userRepository.findByToken(token).orElse(null);
        if(user == null || user.getRole() != UserRole.ADMIN)
            ResponseEntity.status(600).build();

        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();
        imagesDir.mkdirs();

        File imageFile = new File(imagesDir.toString() + File.separator + id + ".webp");
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(imageFile)){
            fileOutputStream.write(file.getBytes());
        }catch (Exception e){
            imageFile.delete();
        }


        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> get(@RequestParam long id) throws IOException {
        File imageFile = new File(imagesDir.toString() + File.separator + id + ".webp");
        if(!imageFile.exists())
            imageFile = new File(imagesDir.toString() + File.separator + "default.png");

        FileInputStream fileInputStream = new FileInputStream(imageFile);
        ResponseEntity<byte[]> ok = ResponseEntity.ok(fileInputStream.readAllBytes());
        fileInputStream.close();
        return ok;
    }

}
