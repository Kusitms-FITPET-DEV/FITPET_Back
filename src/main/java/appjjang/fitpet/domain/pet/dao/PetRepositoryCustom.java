package appjjang.fitpet.domain.pet.dao;

import appjjang.fitpet.domain.catprice.domain.CatPrice;
import appjjang.fitpet.domain.dogprice.domain.DogPrice;

import java.util.List;

public interface PetRepositoryCustom {
    List<DogPrice> getDogPriceList(int age, String breed, String updateCycle, String selfBurden, String priceRate, String oneDayReward);

    List<CatPrice> getCatPriceList(int age, String updateCycle, String selfBurden, String priceRate, String oneDayReward);
}
