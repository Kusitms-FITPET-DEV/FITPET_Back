package appjjang.fitpet.domain.pet.api;

import appjjang.fitpet.domain.pet.application.PetService;
import appjjang.fitpet.domain.pet.dto.response.PetRegisterRequest;
import appjjang.fitpet.domain.pet.dto.response.PetUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "펫 API", description = "펫 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @Operation(summary = "펫 등록", description = "MY펫등록을 진행합니다.")
    @PostMapping
    public ResponseEntity<Void> registerPet(@RequestBody PetRegisterRequest request) {
        petService.savePet(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "펫 수정", description = "등록된 펫의 수정을 진행합니다.")
    @PutMapping
    public ResponseEntity<Void> updatePet(@RequestBody PetUpdateRequest request) {
        petService.updatePet(request);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
