package org.nbgames.core.json;

import java.util.Locale;
import org.json.simple.JSONObject;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class JsonHelper {

    public static int getInt(JSONObject object, String key) {
        return ((Long) object.get(key)).intValue();
    }

    public static String getLanguageSuffix() {
        return "-" + Locale.getDefault().getLanguage();
    }

    public static boolean optBoolean(JSONObject object, String key) {
        if (object.containsKey(key)) {
            return (boolean) object.get(key);
        } else {
            return false;
        }
    }

    public static int optInt(JSONObject object, String key) {
        if (object.containsKey(key)) {
            return getInt(object, key);
        } else {
            return 0;
        }
    }

    public static String optString(JSONObject object, String key) {
        if (object.containsKey(key)) {
            return (String) object.get(key);
        } else {
            return "";
        }
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
