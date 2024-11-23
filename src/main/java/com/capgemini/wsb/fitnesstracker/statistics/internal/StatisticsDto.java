package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;

record StatisticsDto(@Nullable Long Id,
                     Long userId,
                     int totalTrainings,
                     double totalDistance,
                     int totalCaloriesBurned) {
}
