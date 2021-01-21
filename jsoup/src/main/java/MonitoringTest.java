import com.google.gson.Gson;
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
        int errorCnt = 0;
        try {
            List<String> urls = getUrlByApi();
            for (String url : urls) {
                Connection.Response response = getConnectionResponse(url);
                if (response.statusCode() >= 200 && response.statusCode() < 400) {
                    List<String> links = htmlParse(response);

                    for (String link : links) {
                        Connection.Response _response = getConnectionResponse(link);
                        if (_response.statusCode() >= 400) {
                            callErrorApi();
                            errorCnt++;
                        }
                    }
                } else {
                    callErrorApi();
                    errorCnt++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // api 오류 count DB insert
            insertErrorCount(errorCnt);
        }
    }

    private List<String> getUrlByApi() {
        // 도메인 주소 물어보기
        List<String> urls = new ArrayList<>();
        String testJson = "";

        Gson gson = new Gson();
        HashMap<String, Object> json = gson.fromJson(testJson, HashMap.class);
        List<Map<String, String>> buckets = (List<Map<String, String>>) json.get("buckets");
        for (Map<String, String> bucket : buckets) {
            urls.add(bucket.get("key"));
        }

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

    private void insertErrorCount(int errorCnt) {
        // 오류 횟수 insert 메서드
   }
}
