package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    default List<Statistics> getStatisticsByUser(Long userId) {
        return findAll().stream().filter(statistics -> statistics.getUser().getId().equals(userId)).toList();
    }

    default List<Statistics> getStatisticsByMoreCalories(int calories){
        return findAll().stream().filter(statistics -> statistics.getTotalCaloriesBurned() > calories).toList();
    }

}
