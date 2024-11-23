package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

record StatisticsDto(@Nullable Long Id,
                     User user,
                     String lastName,
                     int totalTrainings,
                     double totalDistance,
                     int totalCaloriesBurned) {
}
