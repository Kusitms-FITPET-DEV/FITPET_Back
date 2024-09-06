package appjjang.fitpet.domain.journal.application;

import appjjang.fitpet.domain.journal.dao.JournalRepository;
import appjjang.fitpet.domain.journal.domain.Journal;
import appjjang.fitpet.domain.journal.dto.request.JournalCreateRequest;
import appjjang.fitpet.domain.journal.dto.response.JournalResponse;
import appjjang.fitpet.domain.journalimage.dao.JournalImageRepository;
import appjjang.fitpet.domain.journalimage.domain.JournalImage;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JournalService {
    private final JournalRepository journalRepository;
    private final JournalImageRepository journalImageRepository;
    private final AmazonS3 amazonS3;

    public List<JournalResponse> journalLog() {
        // 모든 Journal 데이터를 조회하고 각 Journal에 해당하는 이미지를 포함하여 JournalResponse로 변환
        return journalRepository.findAll().stream()
                .map(this::convertToJournalResponse)
                .collect(Collectors.toList());
    }
    private JournalResponse convertToJournalResponse(Journal journal) {
        // Journal 데이터를 JournalResponse로 변환
        List<String> imageUrls = journal.getJournalImages().stream()
                .map(JournalImage::getImageUrl)
                .collect(Collectors.toList());

        return JournalResponse.builder()
                .insuranceCompany(journal.getInsuranceCompany())
                .insuranceName(journal.getInsuranceName())
                .profileUrl(journal.getProfileUrl())
                .nickname(journal.getNickname())
                .content(journal.getContent())
                .date(journal.getDate())
                .imageUrls(imageUrls)
                .build();
    }

    public void addJournal(JournalCreateRequest request, List<MultipartFile> imageFiles) {
        // 1. Journal 객체 생성
        Journal journal = Journal.createJournal(
                request.getInsuranceCompany(),
                request.getInsuranceName(),
                request.getProfileUrl(),
                request.getNickname(),
                request.getContent(),
                request.getDate()
        );

        // 2. Journal 객체를 먼저 저장하여 ID를 생성
        journalRepository.save(journal);

        // 3. 이미지 업로드 및 URL 반환
        List<JournalImage> journalImages = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            if (file.isEmpty()) continue; // 파일이 비어있지 않은 경우만 처리

            String imageUrl = uploadFileToS3(file); // 파일을 S3에 업로드하고 URL을 반환
            JournalImage journalImage = new JournalImage();
            journalImage.setImageUrl(imageUrl);
            journalImage.setJournal(journal);
            journalImages.add(journalImage);
        }
        // 4. Journal에 이미지 리스트 추가
        journal.setJournalImages(journalImages);  // Journal 객체에 이미지 리스트를 추가

        // 5. Journal 객체와 관련된 이미지를 저장
        journalRepository.save(journal);
    }
    private String uploadFileToS3(MultipartFile file) {
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
