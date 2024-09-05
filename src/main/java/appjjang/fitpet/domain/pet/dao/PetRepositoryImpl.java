package appjjang.fitpet.domain.pet.dao;

import appjjang.fitpet.domain.catprice.domain.CatPrice;
import appjjang.fitpet.domain.doglist.domain.DogList;
import appjjang.fitpet.domain.dogprice.domain.DogPrice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static appjjang.fitpet.domain.catprice.domain.QCatPrice.catPrice;
import static appjjang.fitpet.domain.doglist.domain.QDogList.dogList;
import static appjjang.fitpet.domain.dogprice.domain.QDogPrice.dogPrice;
import static appjjang.fitpet.domain.pet.domain.QPet.pet;
import static appjjang.fitpet.global.common.constants.PetConstants.*;
import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static org.hibernate.query.results.Builders.fetch;

@Repository
@RequiredArgsConstructor
@Primary
public class PetRepositoryImpl implements PetRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<DogPrice> getDogPriceList(int age, String breed, String updateCycle, String selfBurden, String priceRate, String oneDayReward) {
        DogList searchList = queryFactory
                .selectFrom(dogList)
                .where(dogList.breed.eq(breed))
                .fetchOne();

        if (searchList == null) {
            return Collections.emptyList();
        }

        return queryFactory
                .selectFrom(dogPrice)
                .where(dogPrice.dogList.eq(searchList)
                        .or(
                                dogPrice.insuranceCompany.eq(PETPERMINT)
                                        .and(dogPrice.grade.eq(searchList.getPetpermint()))
                                        .and(dogPrice.updateCycle.eq(updateCycle))
                                        .and(dogPrice.selfBurden.eq(selfBurden))
                                        .and(dogPrice.priceRate.eq(priceRate))
                                        .and(dogPrice.oneDayReward.eq(oneDayReward))
                                        .and(dogPrice.age.eq(age))
                        )
                        .or(
                                dogPrice.insuranceCompany.eq(SAMSUNG)
                                        .and(dogPrice.grade.eq(searchList.getSamsung()))
                                        .and(dogPrice.updateCycle.eq(updateCycle))
                                        .and(dogPrice.selfBurden.eq(selfBurden))
                                        .and(dogPrice.priceRate.eq(priceRate))
                                        .and(dogPrice.oneDayReward.eq(oneDayReward))
                                        .and(dogPrice.age.eq(age))
                        )
                        .or(
                                dogPrice.insuranceCompany.eq(DB)
                                        .and(dogPrice.grade.eq(searchList.getDb()))
                                        .and(dogPrice.updateCycle.eq(updateCycle))
                                        .and(dogPrice.selfBurden.eq(selfBurden))
                                        .and(dogPrice.priceRate.eq(priceRate))
                                        .and(dogPrice.oneDayReward.eq(oneDayReward))
                                        .and(dogPrice.age.eq(age))
                        )
                        .or(
                                dogPrice.insuranceCompany.eq(HYUNDAI)
                                        .and(dogPrice.grade.eq(searchList.getHyundai()))
                                        .and(dogPrice.updateCycle.eq(updateCycle))
                                        .and(dogPrice.selfBurden.eq(selfBurden))
                                        .and(dogPrice.priceRate.eq(priceRate))
                                        .and(dogPrice.oneDayReward.eq(oneDayReward))
                                        .and(dogPrice.age.eq(age))
                        )
                        .or(
                                dogPrice.insuranceCompany.eq(KB)
                                        .and(dogPrice.grade.eq(searchList.getKb()))
                                        .and(dogPrice.updateCycle.eq(updateCycle))
                                        .and(dogPrice.selfBurden.eq(selfBurden))
                                        .and(dogPrice.priceRate.eq(priceRate))
                                        .and(dogPrice.oneDayReward.eq(oneDayReward))
                                        .and(dogPrice.age.eq(age))
                        )
                )
                .fetch();
    }

    @Override
    public List<CatPrice> getCatPriceList(int age, String updateCycle, String selfBurden, String priceRate, String oneDayReward) {
        return queryFactory
                .selectFrom(catPrice)
                .where(catPrice.age.eq(age)
                        .and(catPrice.updateCycle.eq(updateCycle))
                        .and(catPrice.selfBurden.eq(selfBurden))
                        .and(catPrice.priceRate.eq(priceRate))
                        .and(catPrice.oneDayReward.eq(oneDayReward)))
                .fetch();
    }

    @Override
    public String getPetNameById(Long petId){
        return queryFactory
                .select(pet.name)  // Pet의 name 필드를 선택
                .from(pet)
                .where(pet.id.eq(petId))  // petId와 일치하는 Pet 객체를 조회
                .fetchOne();  // 단일 결과 반환
    }
}
