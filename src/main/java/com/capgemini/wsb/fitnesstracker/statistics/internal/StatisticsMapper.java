package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    Training toEntity(TrainingDto trainingDto, User user) {
        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }

    Training toUpdateEntity(TrainingDto trainingDto, Training training, User user)
    {
        if (user != null) {
            training.setUser(user);
        }
        if(trainingDto.startTime() != null)
        {
            training.setStartTime(trainingDto.startTime());
        }
        if(trainingDto.endTime() != null)
        {
            training.setEndTime(trainingDto.endTime());
        }
        if(trainingDto.activityType() != null)
        {
            training.setActivityType(trainingDto.activityType());
        }

        return training;
    }

}
