package appjjang.fitpet.global.config.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    // 환경 변수에서 S3 액세스 키와 시크릿 키를 주입 받습니다.
    @Value("${S3_ACCESS_KEY}")
    private String accessKey;

    @Value("${S3_SECRET_KEY}")
    private String secretKey;

    @Value("${S3_REGION:ap-southeast-2}")  // S3_REGION이 없을 경우 기본 값으로 ap-southeast-2 사용
    private String region;

    @Bean
    public AmazonS3 amazonS3Client() {
        // AWS 자격증명 객체 생성
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        // AmazonS3 클라이언트 생성 및 리전과 자격증명 설정
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)  // 환경 변수에서 읽어온 리전 설정
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))  // 자격증명 설정
                .build();
    }
}
