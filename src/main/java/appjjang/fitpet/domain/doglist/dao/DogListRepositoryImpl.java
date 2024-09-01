package appjjang.fitpet.domain.doglist.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static appjjang.fitpet.domain.doglist.domain.QDogList.dogList;

@Repository
@RequiredArgsConstructor
public class DogListRepositoryImpl implements DogListRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> searchBreedByKeyword(String keyword) {
        return queryFactory
                .select(dogList.breed)
                .from(dogList)
                .where(dogList.breed.like("%" + keyword + "%"))
                .fetch();
    }
}
