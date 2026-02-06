package com.virtadity.manease.repository;

import com.virtadity.manease.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    @Query(value = "SELECT * FROM SUPPLY WHERE delivery_date BETWEEN ?1 AND ?2 ORDER BY delivery_date",
            nativeQuery = true)
    List<Supply> findSupplyBetweenDates(Date fromDate, Date toDate);
}
