package com.sist.jobgem.controller;

import java.io.IOException;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sist.jobgem.service.S3UploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final S3UploadService s3UploadService;

    // 파일 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3UploadService.saveFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }

    // 파일 다운로드
    @GetMapping("/download/{filename}")
    public ResponseEntity<UrlResource> downloadFile(@PathVariable String filename) {
        return s3UploadService.downloadImage(filename);
    }

    // 파일 삭제
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        s3UploadService.deleteImage(filename);
        return ResponseEntity.ok("File deleted successfully");
    }
}
