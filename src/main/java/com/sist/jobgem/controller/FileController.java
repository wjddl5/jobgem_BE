package com.sist.jobgem.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sist.jobgem.service.S3UploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "File", description = "파일처리 API")
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final S3UploadService s3UploadService;

    @Operation(summary = "파일 업로드", description = "선택한 파일 업로드하기")
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

    @Operation(summary = "파일 다운로드", description = "선택한 파일 다운받기")
    @GetMapping("/download/{filename}")
    public ResponseEntity<UrlResource> downloadFile(@PathVariable("filename") String filename) {
        try {
            UrlResource urlResource = s3UploadService.downloadImage(filename);
            String contentDisposition = s3UploadService.getContentDisposition(filename);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(urlResource);
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build(); // 에러 처리
        }
    }

    @Operation(summary = "파일 삭제", description = "선택한 파일 삭제하기")
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable("filename") String filename) {
        s3UploadService.deleteImage(filename);
        return ResponseEntity.ok("File deleted successfully");
    }

}
