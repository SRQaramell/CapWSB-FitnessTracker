package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;

/**
 * Interface (API) for modifying operations on {@link Statistics} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface StatisticsService {

    Statistics createStatistics(Statistics statistics);

    Statistics updateStatistics(Statistics statistics);

    void deleteStatistics(Statistics statistics);

}
