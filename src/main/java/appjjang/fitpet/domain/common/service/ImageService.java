package appjjang.fitpet.domain.common.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final AmazonS3 amazonS3;

    public String uploadFileToS3(MultipartFile file) {
        // S3 업로드 로직
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // S3에 객체 업로드와 동시에 PublicRead 권한 부여
            amazonS3.putObject(new PutObjectRequest("fitpet-image-bucket", fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            // 업로드된 객체의 URL 반환
            return amazonS3.getUrl("fitpet-image-bucket", fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("S3 file upload failed", e);
        }
    }
}
