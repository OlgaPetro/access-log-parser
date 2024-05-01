package mehtods;

import exeptions.SaveException;
import logs.LogEntry;
import logs.UserAgent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class InMemory {

    private static final String TITLE_LINE_LOGS = "IP-адрес, св-во1, св-во2,Дата,Метод, Путь, HTTP-ответ, Размер, Путь к странице, Агент \n";
    private static final String TITLE_LINE_AGENT = "browser,botName, operationSystem; \n";
    private static final File logsFile = new File("src/main/resources/result_logs.csv");
    private static final File agentFile = new File("src/main/resources/result_agent.csv");


    public void saveAgent(List<UserAgent> userAgents) {
        try (Writer fileWriter = new FileWriter(agentFile)) {

            fileWriter.write(TITLE_LINE_AGENT);

            for (UserAgent agentEntry : userAgents) {
                fileWriter.write(toString(agentEntry) + "\n");
            }

        } catch (IOException ex) {
            try {
                throw new SaveException("Запись не корректная");
            } catch (SaveException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private String toString(UserAgent userAgents) {
        return String.format("%s,%s,%s",
                userAgents.getBrowser(),
                userAgents.getBotName(),
                userAgents.getOperationSystem()

        );
    }

    public void save(List<LogEntry> logEntries) {
        try (Writer fileWriter = new FileWriter(logsFile)) {

            fileWriter.write(TITLE_LINE_LOGS);

            for (LogEntry logEntry : logEntries) {
                fileWriter.write(toString(logEntry) + "\n");
            }

        } catch (IOException ex) {
            try {
                throw new SaveException("Запись не корректная");
            } catch (SaveException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //IP-адрес, св-во1, св-во2,Дата,Метод, Путь, HTTP-ответ, Размер, Путь к странице, Аген
    private String toString(LogEntry logEntries) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                logEntries.getIpAddress(),
                logEntries.getPropertyOne(),
                logEntries.getPropertySecond(),
                logEntries.getDateTime(),
                logEntries.getRequestMethod(),
                logEntries.getRequestPath(),
                logEntries.getResponseCode(),
                logEntries.getDataSize(),
                logEntries.getReferer()


        );
    }

}
