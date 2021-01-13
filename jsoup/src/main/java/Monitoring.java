import java.util.Date;

public class Monitoring {
    private String url;
    private int statusCode;
    private String statusMessage;
    private Date testDate;
    private String tag;
    private String parentUrl;

    public Monitoring() { }

    public Monitoring(String url, int statusCode, String statusMessage, Date testDate, String tag, String parentUrl) {
        this.url = url;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.testDate = testDate;
        this.tag = tag;
        this.parentUrl = parentUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }
}
