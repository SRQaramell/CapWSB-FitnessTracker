package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsMapper;
import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
class StatisticsController {

    private final StatisticsServiceImpl statisticsService;
    private final StatisticsMapper statisticsMapper;
    private final UserServiceImpl userService;

    @GetMapping
    public List<StatisticsDto> getAllStatistics() {
        return statisticsService.findAllStatistics()
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }
    @GetMapping("/user/{id}")
    public List<StatisticsDto> getStatisticsByUser(@PathVariable Long id) {
        return statisticsService.getStatisticsByUser(id)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    @GetMapping("/calories/{calories}")
    public List<StatisticsDto> getStatisticsByCalories(@PathVariable int calories) {
        return statisticsService.getStatisticsByMoreCalories(calories)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatisticsDto createStatistics(@RequestBody StatisticsDto statisticsDto) {
        User user = userService.getUser(statisticsDto.userId()).orElseThrow(() -> new IllegalArgumentException("User with ID " + statisticsDto.userId() + " not found"));
        Statistics statistics = statisticsMapper.toEntity(statisticsDto, user);
        Statistics createdStatistics = statisticsService.createStatistics(statistics);

        return statisticsMapper.toDto(createdStatistics);
    }

    @PutMapping("/{id}")
    public StatisticsDto updateStatistics(@PathVariable Long id, @RequestBody StatisticsDto StatisticsDto) {
        return null;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatistics(@PathVariable Long statisticsId)
    {

    }




}