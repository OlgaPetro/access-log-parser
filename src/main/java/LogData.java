public class LogData {

    private String ipAddress; //IP-адрес клиента, который сделал запрос к серверу
    private String propertyOne; //Пропущенное свойство 1
    private String propertySecond; //Пропущенное свойство 2
    private String dateTime; //Дата и время запроса в квадратных скобках.
    private String requestMethod; //Метод запроса (в примере выше — GET) и путь, по которому сделан запрос.
    private String requestPath; //Путь, по которому сделан запрос.
    private int responseCode; //Код HTTP-ответ
    private int dataSize; //Размер отданных данных в байтах
    private String referer; //Путь к странице, с которой перешли на текущую страницу
    private String userAgent; //Информация о браузере или другом клиенте, который выполнил запрос.

    public LogData(String ipAddress, String propertyOne, String propertySecond, String dateTime, String requestMethod, String requestPath, int responseCode, int dataSize, String referer, String userAgent) {
        this.ipAddress = ipAddress;
        this.propertyOne = propertyOne;
        this.propertySecond = propertySecond;
        this.dateTime = dateTime;
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
        this.responseCode = responseCode;
        this.dataSize = dataSize;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    public LogData() {
    }
  public LogData(String userAgent) {
        this.userAgent = userAgent;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPropertyOne() {
        return propertyOne;
    }

    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }

    public String getPropertySecond() {
        return propertySecond;
    }

    public void setPropertySecond(String propertySecond) {
        this.propertySecond = propertySecond;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    public static boolean checkUserAgent(String fragment, String botName) {
        return fragment.equals(botName);
    }


    @Override
    public String toString() {
        return "LogData{" +
                "ipAddress='" + ipAddress + 'p' +
                ", propertyOne='" + propertyOne + '\'' +
                ", propertySecond='" + propertySecond + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", responseCode=" + responseCode +
                ", dataSize=" + dataSize +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}

