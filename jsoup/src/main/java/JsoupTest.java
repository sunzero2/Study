import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
        // 1. return Connection.Response
        Connection.Response testUrl = Jsoup.connect("http://www.naver.com").execute();
        int code = testUrl.statusCode(); // HTTP Status Code
        Document html = testUrl.parse(); // HTML
        Elements eles = html.select("img");
        for (Element ele: eles) {
            System.out.println(ele.attr("data-src"));
            // 2. replay 1
        }
    }
}
