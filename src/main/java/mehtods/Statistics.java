package mehtods;

import logs.LogEntry;
import logs.UserAgent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class Statistics {

    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    HashSet<String> addressList = new HashSet<>();
    HashMap<String, Integer> operationSystemStatistics = new HashMap<>();
    HashMap<String, Integer> browserStatistics = new HashMap<>();
    HashSet<String> nonExistingPages = new HashSet<>();
    private long uniqueIpAddresses;
    private long browserVisits;
    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
    }

    public void addEntry(LogEntry logEntry) {
        totalTraffic += logEntry.getDataSize();

        if (logEntry.getDateTime().isBefore(minTime)) {
            minTime = logEntry.getDateTime();
        }

        if (logEntry.getDateTime().isAfter(maxTime)) {
            maxTime = logEntry.getDateTime();
        }

        if (logEntry.getResponseCode() == 200) {
            addressList.add(logEntry.getRequestPath());
        }
        UserAgent userAgent = new UserAgent(logEntry.getUserAgentFullData());
        String operationSystem = userAgent.getOperationSystem();
        operationSystemStatistics.put(operationSystem, operationSystemStatistics.getOrDefault(operationSystem,
                0) + 1);


        if (logEntry.getResponseCode() == 404) {
            nonExistingPages.add(logEntry.getRequestPath());
        }

        operationSystemStatistics.put(operationSystem, operationSystemStatistics.getOrDefault(operationSystem, 0) + 1);

        String browser = userAgent.getBrowser();
        browserStatistics.put(browser, browserStatistics.getOrDefault(browser, 0) + 1);
        if (logEntry.getResponseCode() == 200) {
            addressList.add(logEntry.getRequestPath());
        }

        if (!browser.contains("bot")) {
            browserVisits++;
            uniqueIpAddresses += Stream.of(logEntry.getIpAddress())
                    .distinct()
                    .count();
        }
    }

    public HashMap<String, Double> calculateOperationSystemShare() {
        HashMap<String, Double> operationSystemShare = new HashMap<>();
        HashSet<String> allowedSystems = new HashSet<>(Arrays.asList("Chrome", "Safari", "Ubuntu", "OPiOS", "GSA",
                "SamsungBrowser", "CriOS"));

        if (operationSystemStatistics.size() == 0) {
            System.out.println("Отсутствуют данные по операционным системам.");
            return operationSystemShare;
        }

        int totalSystems = 0;
        for (int value : operationSystemStatistics.values()) {
            totalSystems += value;
        }

        for (Map.Entry<String, Integer> entry : operationSystemStatistics.entrySet()) {
            if (allowedSystems.contains(entry.getKey())) {
                double share = (double) entry.getValue() / totalSystems;
                operationSystemShare.put(entry.getKey(), share);
            }
        }

        return operationSystemShare;
    }

    public HashMap<String, Double> calculateBrowserShare() {
        HashMap<String, Double> browserShare = new HashMap<>();
        if (browserStatistics.size() == 0) {
            System.out.println("Нет данных по браузерам.");
            return browserShare;
        }

        int totalBrowsers = 0;
        for (int value : browserStatistics.values()) {
            totalBrowsers += value;
        }

        for (Map.Entry<String, Integer> entry : browserStatistics.entrySet()) {
            double share = (double) entry.getValue() / totalBrowsers;
            browserShare.put(entry.getKey(), share);
        }

        return browserShare;
    }

    public void getTrafficRate() {
        long diffInHours = Duration.between(maxTime, minTime).toHours();

        if (diffInHours == 0) {
            System.out.println("Нет промежутка времени и данные не получить");
        } else {
            double averageTrafficRate = totalTraffic / diffInHours;
            System.out.println("Средний объем трафика за час: " + averageTrafficRate + " байт.");
        }

    }
    public double calculateAverageVisitsHour() {
        long diffInHours = Duration.between(maxTime, minTime).toHours();
        return (double) browserVisits / diffInHours;
    }

    public double calculateAverageVisitsUser() {
        if (uniqueIpAddresses == 0) {
            return 0;
        }
        return (double) browserVisits / uniqueIpAddresses;
    }
}