package az.qonaqol.qonaqol.util;

import az.qonaqol.qonaqol.dao.entity.EventPhotoEntity;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FileUtil {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.defaultFolder}")
    private String defaultBaseFolder;

    private final MinioClient minioClient;



    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public void uploadFile(MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
          //  String fileUrl = url + defaultBaseFolder + file.getOriginalFilename();

           //// userMetadata.put("contentType", eventPhoto.getType());
            try {
                InputStream inputStream = file.getInputStream();
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .stream(inputStream, inputStream.available(), -1)
                        //.userMetadata(userMetadata)
                        .build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] downloadFile(String fileName) {
        try {
            InputStream inputStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build());
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            // Instead of throwing a RuntimeException, you may want to log the error or handle it appropriately
            throw new RuntimeException("Failed to download image: " + e.getMessage());
        }
    }

}
