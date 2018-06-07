package com.megan.webp3l.repository;

import com.megan.webp3l.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    Season findSeasonById(int id);
    List<Season> findSeasonsByNameContaining(String name);

    @Query(value = "SELECT * FROM tbl_season S WHERE (:startDate BETWEEN S.season_start_date " +
            "AND S.season_finish_date) AND S.branch_id = :branch", nativeQuery = true)
    Season getPercentage(@Param("startDate") Date startDate, @Param("branch") int branch);
}
