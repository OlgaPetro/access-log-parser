package logs;

public class LogParser {

    public static LogEntry parseLogLine(String logLine) {
        LogEntry logData = new LogEntry();

        String[] lineParts = logLine.split(" ");
        if (lineParts.length >= 10) {
            logData.setIpAddress(lineParts[0]);
            logData.setPropertyOne(lineParts[1]);
            logData.setPropertySecond(lineParts[2]);
            logData.setDateTime(lineParts[3] + " " + lineParts[4]);
            logData.setRequestMethod(lineParts[5].replace("\"", ""));
            logData.setRequestPath(lineParts[6]);
            logData.setResponseCode(Integer.parseInt(lineParts[8]));
            logData.setDataSize(Integer.parseInt(lineParts[9]));
            logData.setReferer(lineParts[10].equals("-") ? "" : lineParts[10]);
            logData.setUserAgent(lineParts.length > 13 ? lineParts[13].replace("\"", "") : "-");

            return logData;
        }

        return null;
    }

    public static String extractBotNameFromUserAgent(String userAgent) {
        String botName;

            String[] botTypeAndVersion = userAgent.split("/");
            botName = botTypeAndVersion[0];

        return botName;
    }

}