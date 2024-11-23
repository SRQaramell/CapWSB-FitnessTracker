package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsProvider, StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics createStatistics(Statistics statistics) {
        if (statistics == null) {
            throw new IllegalArgumentException("Statistics cannot be null");
        }
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics updateStatistics(Statistics statistics) {
        if (statistics == null || statistics.getId() == null) {
            throw new IllegalArgumentException("Statistics or its ID cannot be null for update");
        }
        if (!statisticsRepository.existsById(statistics.getId())) {
            throw new IllegalArgumentException("Statistics with ID " + statistics.getId() + " does not exist");
        }
        return statisticsRepository.save(statistics);
    }

    @Override
    public void deleteStatistics(Statistics statistics) {
        if (statistics == null || statistics.getId() == null) {
            throw new IllegalArgumentException("Statistics or its ID cannot be null for delete");
        }
        statisticsRepository.delete(statistics);
    }

    @Override
    public List<Statistics> getStatisticsByUser(Long userId) {
        return statisticsRepository.getStatisticsByUser(userId);
    }

    @Override
    public List<Statistics> getStatisticsByMoreCalories(int calories) {
        return statisticsRepository.getStatisticsByMoreCalories(calories);
    }

    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        return Optional.empty();
    }

    @Override
    public List<Statistics> findAllStatistics() {
        return statisticsRepository.findAll();
    }
}
