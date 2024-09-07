package com.adalbertosn.uploadfilewebclient.api.contoller;

import com.adalbertosn.uploadfilewebclient.core.ReactiveUpload;
import com.adalbertosn.uploadfilewebclient.core.UploadDisco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

@RestController
public class UploadController {

    Logger log = LoggerFactory.getLogger(UploadController.class);
    private final Path basePath = Paths.get("D:\\documentos-disco");
    @Autowired
    private UploadDisco disco;

    @Autowired
    ReactiveUpload uploadService;

    @PostMapping(path = "/upload" /*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile multipartFile) {
        //log.info("Request contains, File: " + file.getOriginalFilename());
        log.info("Request contains, File: "+ multipartFile.getOriginalFilename());
        // Add your processing logic here
        disco.salvar(multipartFile);
        return ResponseEntity.ok("Success");
    }

    @PostMapping(path = "/uploadDisco")
    public ResponseEntity<String> uploadDisco(@RequestParam("file") MultipartFile multipartFile) {
        //log.info("Request contains, File: " + file.getOriginalFilename());
        log.info("Request contains, File: "+ multipartFile.getOriginalFilename());
        // Add your processing logic here
        disco.salvar(multipartFile);
        return ResponseEntity.ok("Success");
    }

    @PostMapping(value = "/test")
    public String test(@RequestBody String dado) {
        log.info("Request contains, File: ");
        return dado;
    }

    @PostMapping(path = "/saveFileDisco"/* , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE*/)
    private String saveFileDisco(@RequestBody FilePart filePart) throws Exception {
        log.info("handling file upload {}", filePart.filename());

        // if a file with the same name already exists in a repository, delete and recreate it
        final String filename = filePart.filename();
        File file = new File(filename);
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            //return Mono.error(e); // if creating a new file fails return an error
            throw new Exception(e.getMessage());
        }

        filePart.transferTo(basePath.resolve(filePart.filename()));
        //uploadDisco.salvarFileReactivo(filePart);
        return filename;
    }

    @PostMapping(path = "/uploadPdf1")
    @ResponseBody
    public Mono<String> uploadPdf1(@RequestParam("file") final MultipartFile multipartFile) {
        return uploadService.uploadPdf(multipartFile.getResource());
    }

    @PostMapping(path = "/upload/multipart1"/* , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE*/)
    @ResponseBody
    //public Mono<String> uploadMultipart1(@RequestParam("file") final MultipartFile[] multipartFile) {
    public Mono<String> uploadMultipart1(@RequestParam("file") final MultipartFile[] multipartFile) {
        int cuant = multipartFile.length;
        for (int i = 0; i < cuant - 1; i++) {
            uploadService.uploadMultipart(multipartFile[i]).subscribe();
        }
        //uploadService.uploadMultipart(multipartFile[0]);
        if (cuant > 0) {
            return uploadService.uploadMultipart(multipartFile[cuant-1]);
        }
        return Mono.just("Success");
    }
}
