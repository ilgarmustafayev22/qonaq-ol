package az.qonaqol.qonaqol.util;


import az.qonaqol.qonaqol.exception.PhotoNotFoundException;
import az.qonaqol.qonaqol.exception.UserNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtil {

    private final AmazonS3 s3Client;
    private static final String BUCKET_NAME = "qonaqol";

    public void uploadFile(MultipartFile file) {
        if (file == null) {
            throw new PhotoNotFoundException("No file to upload");
        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        try (InputStream inputStream = file.getInputStream()) {
            s3Client.putObject(BUCKET_NAME, file.getOriginalFilename(), inputStream, metadata);
        } catch (IOException e) {
            log.error("Error occurred while uploading file to S3", e);
        }
    }

    public void uploadFiles(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            throw new PhotoNotFoundException("No files to upload");
        }
        for (MultipartFile file : files) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            try (InputStream inputStream = file.getInputStream()) {
                s3Client.putObject(BUCKET_NAME, file.getOriginalFilename(), inputStream, metadata);
            } catch (IOException e) {
                log.error("Error occurred while uploading files to S3", e);
            }
        }
    }

    public void deleteFile(String fileName){
        s3Client.deleteObject(BUCKET_NAME,fileName);
    }

    // public byte[] downloadFile(String fileName) {
    //     S3Object s3Object = s3Client.getObject(BUCKET_NAME, fileName);
    //     try (S3ObjectInputStream inputStream = s3Object.getObjectContent()) {
    //         return IOUtils.toByteArray(inputStream);
    //     } catch (IOException e) {
    //         throw new PhotoNotFoundException("Photo not found on S3: " + fileName);
    //     }
    // }

}

























