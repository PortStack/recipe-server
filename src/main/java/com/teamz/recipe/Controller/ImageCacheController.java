package com.teamz.recipe.Controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ImageCacheController {

    private static final String CACHE_DIRECTORY = "/Users/gimmunjin/Study/recipe-server/images/";


    @GetMapping(value="/images/{folderName}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable("folderName") String folderName,@PathVariable("imageName") String imageName) throws IOException {
        File imageFile = new File(CACHE_DIRECTORY + folderName +"/"+ imageName);
        System.out.println(imageFile.toString());
        if (imageFile.exists()) {
            // 이미지 파일이 캐시 디렉토리에 존재하면 파일을 읽어서 바이트 배열로 반환합니다.
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
            fileInputStream.close();
            return new ResponseEntity<byte[]>(imageBytes, HttpStatus.OK);
        } else {
            // 이미지 파일이 캐시 디렉토리에 존재하지 않으면 에러를 반환합니다.
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/images")
    @ResponseBody
    public Mono<String> uploadImage(@RequestPart("file") FilePart filePart) throws IOException {
        String originalFilename = filePart.filename();
        String uniqueFileName = generateUniqueFileName(originalFilename);

        // 파일을 로컬 캐시 디렉토리에 저장합니다.
        File cacheFile = new File(CACHE_DIRECTORY + uniqueFileName);
        filePart.transferTo(cacheFile);

        return Mono.just(uniqueFileName);
    }

    private String generateUniqueFileName(String originalFilename) {
        // 이미지 파일을 식별하는 고유한 이름을 생성합니다.
        // 여기서는 간단히 타임스탬프를 사용합니다.
        return System.currentTimeMillis() + "_" + originalFilename;
    }

    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Mono<String> handleImageNotFoundException(ImageNotFoundException ex) {
        return Mono.just("Image not found");
    }

    private static class ImageNotFoundException extends RuntimeException {
    }

}
