package appjjang.fitpet.domain.pet.api;

import appjjang.fitpet.domain.pet.application.PetService;
import appjjang.fitpet.domain.pet.dto.response.PetRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "펫 API", description = "펫 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @Operation(summary = "펫 등록", description = "MY펫등록을 진행합니다.")
    @PostMapping
    public ResponseEntity<Void> registerPet(@RequestBody PetRegisterRequest request) {
        log.info(request.getPetName());
        petService.savePet(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
