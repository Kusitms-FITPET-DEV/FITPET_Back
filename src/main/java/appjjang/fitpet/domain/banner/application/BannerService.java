package appjjang.fitpet.domain.banner.application;

import appjjang.fitpet.domain.banner.dao.BannerRepository;
import appjjang.fitpet.domain.banner.domain.Banner;
import appjjang.fitpet.domain.common.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BannerService {
    private final ImageService imageService;
    private final BannerRepository bannerRepository;

    public void saveBannerImage(MultipartFile file) {
        String bannerUrl = imageService.uploadFileToS3(file);
        bannerRepository.save(Banner.createBanner(bannerUrl));
    }

    public List<String> getBannerUrlList() {
        return bannerRepository.findAll().stream()
                .map(Banner::getBannerUrl)
                .collect(Collectors.toList());
    }
}
