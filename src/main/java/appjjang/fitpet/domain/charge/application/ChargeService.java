package appjjang.fitpet.domain.charge.application;

import appjjang.fitpet.domain.charge.dao.ChargeRepository;
import appjjang.fitpet.domain.charge.domain.Agree;
import appjjang.fitpet.domain.charge.domain.Authentication;
import appjjang.fitpet.domain.charge.domain.Charge;
import appjjang.fitpet.domain.charge.domain.Notice;
import appjjang.fitpet.domain.charge.dto.request.InsuranceChargeRequest;
import appjjang.fitpet.domain.insurance.domain.Insurance;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static appjjang.fitpet.domain.charge.domain.QAgree.agree;
import static appjjang.fitpet.domain.charge.domain.QNotice.notice;
import static appjjang.fitpet.domain.insurance.domain.QInsurance.insurance;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.authentication;

@Service
@RequiredArgsConstructor
@Transactional
public class ChargeService {
    private final ChargeRepository chargeRepository;
    private final AmazonS3 amazonS3;

    public void saveCharge(InsuranceChargeRequest request, Insurance insurance){
        Authentication authentication = Authentication.builder()
                .receiptUrl(request.getReceiptUrl())
                .medicalExpensesUrl(request.getMedicalExpensesUrl())
                .etcUrl(request.getEtcUrl())
                .build();
        Notice notice = Notice.builder()
                .message(request.getMessage())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        Agree agree = Agree.builder()
                .essentialAgree(request.getEssentialAgree())
                .optionalAgree(request.getOptionalAgree())
                .build();

        chargeRepository.save(Charge.createCharge(
                request.getType(),
                request.getVisitTime(),
                authentication,
                request.getAccountHolder(),
                request.getBank(),
                request.getAccount(),
                notice,
                agree
        ));
    }
    public Map<String, String> uploadDocuments(MultipartFile receipt, MultipartFile medicalExpenses, MultipartFile etc) {
        String receiptUrl = uploadFileToS3(receipt);
        String medicalExpensesUrl = uploadFileToS3(medicalExpenses);
        String etcUrl = uploadFileToS3(etc);

        Map<String, String> urls = new HashMap<>();
        urls.put("receiptUrl", receiptUrl);
        urls.put("medicalExpensesUrl", medicalExpensesUrl);
        urls.put("etcUrl", etcUrl);

        return urls;
    }
    private String uploadFileToS3(MultipartFile file) {
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
