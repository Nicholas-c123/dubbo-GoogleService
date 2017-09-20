package xin.nicholas.google;

import java.util.List;

/**
 * Created by Nicholas on 17-9-18.
 */
public interface GoogleService {
    List<String> getTiles(String keywords, Integer limits);
    List<String> getDescriptions(String keywords, Integer limits);
}
