package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import java.util.Date;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findByActivityType(ActivityType activityType){
        return findAll().stream().filter(training -> training.getActivityType().equals(activityType)).toList();
    }

    default List<Training> findAfterDate(Date date) {
        return findAll().stream().filter(training -> training.getEndTime().after(date)).toList();
    }

    default List<Training> findByUser(Long userId)
    {
        return findAll().stream().filter(training -> training.getUser().getId().equals(userId)).toList();
    }

}
