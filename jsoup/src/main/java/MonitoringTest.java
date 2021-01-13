import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class MonitoringTest {
    private final static Logger LOG = Logger.getGlobal();
    private final String RGX = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
    Pattern pattern = Pattern.compile(RGX);

    public void monitoring() {
        // 1. API 통신하여 테스트할 URL 가져옴
        // 테스트 URL의 status code가 >= 200 < 400인가? 맞으면 2, 아니면 3
        // 2. 크롤링해서 img 주소랑 a 주소 가져옴
        // 3. 크롤링하여 가져온 주소를 테스트함
        // status code가 < 200 > 400인가? 맞으면 4
        // 4. 에러 발생 시 호출하는 API 호출함
        // 5. 데이터베이스에 데이터 삽입하기
        try {
            List<Monitoring> monitorings = new ArrayList<>();
            List<String> urls = getUrlByApi();
            for (String url : urls) {
                Connection.Response response = getConnectionResponse(url);
                if (response.statusCode() >= 200 && response.statusCode() < 400) {
                    List<String> links = htmlParse(response);

                    for (String link : links) {
                        Connection.Response _response = getConnectionResponse(link);
                        Monitoring monitoring = new Monitoring(link, _response.statusCode(), _response.statusMessage(), new Date(), _response.parse().tagName(), url);
                        monitorings.add(monitoring);
                        if (_response.statusCode() >= 400) {
                            callErrorApi();
                        }
                    }
                } else {
                    callErrorApi();
                }
            }

            insertTestResponse(monitorings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> getUrlByApi() {
        List<String> urls = new ArrayList<>();
        urls.add("https://google.com");
        urls.add("https://naver.com");
        urls.add("https://youtube.com");
        // DB 저장 코드 추가하기 (URL) Monitoring에 안담아도 됨.
        return urls;
    }

    private Connection.Response getConnectionResponse(String url) throws IOException {
        return Jsoup.connect(url).ignoreContentType(true).execute();
    }

    private List<String> htmlParse(Connection.Response response) throws IOException {
        Document doc = response.parse();
        Elements elements = doc.select("img");
        elements.addAll(doc.select("a"));

        return getUrlByHtml(elements);
    }

    private List<String> getUrlByHtml(Elements elements) {
        List<String> urls = new ArrayList<>();
        for (Element e : elements) {
            String tag = e.tagName();
            String attr = tag.equals("a") ? "href" : "src";
            if (e.attr(attr).indexOf("/") == 0) {
                String url = e.attr(attr).substring(1);
                e.attr(attr, e.baseUri() + url);
            }

            if (pattern.matcher(e.attr(attr)).matches()) {
                urls.add(e.attr(attr));
            }
        }

        return urls;
    }

    private void callErrorApi() {
        LOG.info("Call Error API");
    }

    private void insertTestResponse(List<Monitoring> monitorings) {
        for (Monitoring monitoring : monitorings) {
            // Response 테이블에 넣는 메서드
        }
    }
}
