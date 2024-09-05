package appjjang.fitpet.domain.pet.domain;

import appjjang.fitpet.domain.insurance.domain.Insurance;
import appjjang.fitpet.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private String phone;
    private boolean isInsurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL)
    private Insurance insurance;

    @Builder
    private Pet(String name, Species species, String breed, int birthYear, String phone, Member member, boolean isInsurance) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthYear = birthYear;
        this.phone = phone;
        this.member = member;
        this.isInsurance = isInsurance;
    }

    public static Pet createPet(String name, Species species, String breed, int birthYear, String phone, Member member) {
        Pet pet = Pet.builder()
                .name(name)
                .species(species)
                .breed(breed)
                .birthYear(birthYear)
                .phone(phone)
                .member(member)
                .isInsurance(false)
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

    public Pet delete() {
        this.getMember().getPets().remove(this);
        return this;
    }

    public void updateIsInsurance(){
        this.isInsurance = true;
    }
}
