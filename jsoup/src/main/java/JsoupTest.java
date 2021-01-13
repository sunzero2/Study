import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
        // 1. return Connection.Response
//        Connection.Response testUrl = getUrlConnectionResponse("https://www.naver.com");
//        List<String> elements = htmlParser(testUrl);
//        System.out.println(testUrl.url().toString());
//        System.out.println(elements);

        MonitoringTest test = new MonitoringTest();
        test.monitoring();
    }

    public static Connection.Response getUrlConnectionResponse(String url) throws IOException {
        return Jsoup.connect(url).execute();
    }

    public static List<String> htmlParser(Connection.Response response) throws IOException {
        List<String> elements = new ArrayList<>();
        System.out.println(response.statusMessage());
        int code = response.statusCode();
        if (code == 200) {
            Document html = response.parse();

            // get img
            Elements imgs = html.select("img");
            for (Element img : imgs) {
                if (img.attr("data-src").length() > 0) {
                    elements.add(img.attr("data-src")); // src, data-src
                } else {
                    elements.add(img.attr("src"));
//                    System.out.println(img);
                }
            }

            // get a
            Elements links = html.select("a");
            for (Element link : links) {
                if (link.attr("href").indexOf("h") == 0) {
                    elements.add(link.attr("href"));
                }
            }
        }

        return elements;
    }
}
