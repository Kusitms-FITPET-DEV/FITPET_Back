package appjjang.fitpet.domain.pet.api;

import appjjang.fitpet.domain.pet.application.PetService;
import appjjang.fitpet.domain.pet.dto.request.PetRegisterRequest;
import appjjang.fitpet.domain.pet.dto.request.PetUpdateRequest;
import appjjang.fitpet.domain.pet.dto.response.OwnPetListResponse;
import appjjang.fitpet.domain.pet.dto.response.SinglePetQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static appjjang.fitpet.global.common.constants.PetConstants.PRICE_RATE;


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

    @Operation(summary = "펫 단건 조회", description = "특정 펫의 정보 및 견적 정보를 조회합니다.")
    @GetMapping("/{petId}")
    public SinglePetQueryResponse getPetInfo(@PathVariable Long petId,
                                             @RequestParam(defaultValue = PRICE_RATE) String priceRate) {
        return petService.getPet(petId, priceRate);
    }

    @Operation(summary = "등록된 펫 모두 조회", description = "사용자에게 등록된 모든 펫을 조회합니다.")
    @GetMapping
    public OwnPetListResponse getPetsInfo() {
        return petService.getPetsInfo();
    }
}
