import exeptions.OperationAttemptException;
import logs.LogEntry;
import logs.UserAgent;
import mehtods.Statistics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static constants.Constants.GOOGLE_BOT_NAME;
import static constants.Constants.YANDEX_BOT_NAME;

public class Main {
    public static void main(String[] args) {

        int count = 0;
        String path;
        Statistics statistics = new Statistics();

        while (true) {
            System.out.print("Введите путь к файлу: ");
            path = new Scanner(System.in).nextLine();
            File file = new File(path);

            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (isDirectory) {
                System.out.println("Введенный путь является папкой.");
                continue;
            }

            if (!fileExists) {
                System.out.println("Введенный файл не существует.");
                continue;
            }
            count++;

            System.out.println("Введенный путь к файлу указан верно.");
            System.out.println("Это название файла номер " + count);

            int totalLines = 0;
            int yandexBotCount = 0;
            int googleBotCount = 0;
            List<LogEntry> logDataList = new ArrayList<>();
            List<UserAgent> agentDataList = new ArrayList<>();

            FileReader fileReader;
            try {

                fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    totalLines++;
                    int length = line.length();
                    if (length > 1024) {
                        throw new OperationAttemptException("Длина текста в строке " + totalLines + " более 1024 символов");
                    }



                    LogEntry logData = new LogEntry(line);
                    if (logData != null) {
                        logDataList.add(logData);
                        statistics.addEntry(logData);
                    }

                    if (logData.getUserAgentFullData() != null || logData.getUserAgentFullData().equals("-")) {
                        UserAgent userAgent = new UserAgent(logData.getUserAgentFullData());
                        agentDataList.add(userAgent);
                        String botNameFromUserAgent = userAgent.getBotName();
                        if (botNameFromUserAgent != null) {
                            if (LogEntry.checkUserAgent(botNameFromUserAgent, YANDEX_BOT_NAME)) {
                                yandexBotCount++;
                            }

                            if (LogEntry.checkUserAgent(botNameFromUserAgent, GOOGLE_BOT_NAME)) {
                                googleBotCount++;
                            }
                        }
                    }
                }

                statistics.getTrafficRate();
                reader.close();
            } catch (FileNotFoundException e) {
                throw new OperationAttemptException("Файл не найден");
            } catch (IOException e) {
                throw new OperationAttemptException("Ошибка ввода или вывода");
            } catch (OperationAttemptException e) {
                System.out.println(e.getMessage());
            }

            int totalBotCount = yandexBotCount + googleBotCount;

            System.out.println("Количество строк в файле: " + totalLines);
            System.out.println("Сумма YandexBot и Googlebot составляет: " + totalBotCount);
            System.out.println("Количество запросов от " + YANDEX_BOT_NAME + " составляет : " + yandexBotCount);
            System.out.println("Количество запросов от " + GOOGLE_BOT_NAME + " составляет : " + googleBotCount);
            System.out.println("Доля запросов от " + YANDEX_BOT_NAME + " составляет : " + String.format("%.2f", (yandexBotCount * 1.0 / totalBotCount)));
            System.out.println("Доля запросов от " + GOOGLE_BOT_NAME + " составляет : " + String.format("%.2f", (googleBotCount * 1.0 / totalBotCount)));
            

            System.out.println("Доля операционной системы " + statistics.calculateOperationSystemShare());
            break;
        }

    }
}

