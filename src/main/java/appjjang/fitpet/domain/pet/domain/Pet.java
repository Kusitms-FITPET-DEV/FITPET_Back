package appjjang.fitpet.domain.pet.domain;

import appjjang.fitpet.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Species species;
    private String breed;
    private int birthYear;
    private String etc;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Pet(String name, Species species, String breed, int birthYear, String etc, String phone, Member member) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthYear = birthYear;
        this.etc = etc;
        this.phone = phone;
        this.member = member;
    }

    public static Pet createPet(String name, Species species, String breed, int birthYear, String etc, String phone, Member member) {
        Pet pet = Pet.builder()
                .name(name)
                .species(species)
                .breed(breed)
                .birthYear(birthYear)
                .etc(etc)
                .phone(phone)
                .member(member)
                .build();
        member.getPets().add(pet);
        return pet;
    }

    public void updatePet(String name, Species species, String breed, int birthYear) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthYear = birthYear;
    }
}
