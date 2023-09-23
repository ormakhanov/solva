package com.example.solva.repository;

import com.example.solva.enums.CategoryEnum;
import com.example.solva.models.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface LimitRepository extends JpaRepository<LimitEntity,Long> {
    LimitEntity findFirstByUserAccountAndCategoryOrderByLimitDatetimeDesc(String account, CategoryEnum category);

    @Query("SELECT l FROM LimitEntity l " +
            "WHERE l.userAccount = :userAccount " +
            "AND l.limitDatetime = (SELECT MAX(l2.limitDatetime) FROM LimitEntity l2 WHERE l2.userAccount = :userAccount AND l2.category = l.category) ")
    List<LimitEntity> findAllByUserAccountAndCategory(@Param("userAccount") String userAccount);


}
