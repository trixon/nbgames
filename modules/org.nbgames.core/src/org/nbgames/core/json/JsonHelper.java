package org.nbgames.core.json;

import java.util.Locale;
import org.json.simple.JSONObject;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class JsonHelper {

    public static String getLanguageSuffix() {
        return "-" + Locale.getDefault().getLanguage();
    }

    public static String parseLocalizedKey(JSONObject jsonObject, String key) {
        String value = "";
        String localizedKey = key + getLanguageSuffix();

        if (jsonObject.containsKey(localizedKey)) {
            value = (String) jsonObject.get(localizedKey);
        } else if (jsonObject.containsKey(key)) {
            value = (String) jsonObject.get(key);
        }

        return value;
    }

}
