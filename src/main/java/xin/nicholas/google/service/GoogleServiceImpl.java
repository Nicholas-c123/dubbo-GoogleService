package xin.nicholas.google.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.nicholas.google.GoogleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicholas on 17-9-20.
 */
public class GoogleServiceImpl implements GoogleService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    public List<String> getTiles(String s, Integer limit) {
        HttpGet get = new HttpGet("https://www.google.com/search?q=" + s);
        String soup = null;
        try (CloseableHttpResponse response = httpClient.execute(get)){
            HttpEntity entity = response.getEntity();
            soup = EntityUtils.toString(entity);
        } catch (IOException e) {
            logger.error("failed to get response",e);
        }
        logger.info(soup != null ? "no response" : soup.substring(30));
        Document doc = Jsoup.parse(soup);
        Elements elements = doc.select("#rso > div > div > div > div > div > h3 > a");
        List<String> res = new ArrayList<>();
        if (limit == null)
            limit = 20;
        for (Element e : elements)
            if (res.size() < limit)
                res.add(e.html() + s);
            else
                break;
        return res;
    }

    public List<String> getDescriptions(String s, Integer limit) {
        return null;
    }
}
