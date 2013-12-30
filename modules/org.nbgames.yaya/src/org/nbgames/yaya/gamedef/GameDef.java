package org.nbgames.yaya.gamedef;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.nbgames.core.json.JsonHelper;
import se.trixon.almond.FileHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum GameDef {

    INSTANCE;
    private LinkedList<GameType> mGameTypes;
    private String mVersionDate;
    private String mVersionName;

    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mVersionDate).append("\n");
        stringBuilder.append(mVersionName).append("\n");

        for (GameType gameType : mGameTypes) {
            stringBuilder.append(gameType.dump());
        }

        return stringBuilder.toString();
    }

    public String[] getIdArray() {
        String[] result = new String[mGameTypes.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = mGameTypes.get(i).getId();
        }

        return result;
    }

    public String getTitle(String id) {
        for (GameType gameType : mGameTypes) {
            if (gameType.getId().equalsIgnoreCase(id)) {
                return gameType.getTitle();
            }
        }
        // TODO Throw something?
        return "";
    }

    public String[] getTitleArray() {
        String[] result = new String[mGameTypes.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = mGameTypes.get(i).getTitle();
        }

        return result;
    }

    public GameType getType(String id) {
        for (GameType gameType : mGameTypes) {
            if (gameType.getId().equalsIgnoreCase(id)) {
                return gameType;
            }
        }
        // TODO Throw something?
        return getType("default");
    }

    public String getVersionDate() {
        return mVersionDate;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public void init() {
        final String resourcesPath = "/org/nbgames/yaya/gamedef/gameDef.json";
        InputStream inputStream = this.getClass().getResourceAsStream(resourcesPath);

        String jsonString = FileHelper.convertStreamToString(inputStream);
        parse(jsonString);
    }

    private void parse(String jsonString) {
        JSONObject gameDefObject = (JSONObject) JSONValue.parse(jsonString);
        mGameTypes = new LinkedList<GameType>();

        mVersionDate = (String) gameDefObject.get("versionDate");
        mVersionName = (String) gameDefObject.get("versionName");

        JSONArray gamesArray = (JSONArray) gameDefObject.get("games");

        for (int i = 0; i < gamesArray.size(); i++) {
            JSONObject gameObject = (JSONObject) gamesArray.get(i);

            GameType gameType = new GameType();
            gameType.setAuthor((String) gameObject.get("author"));
            gameType.setId((String) gameObject.get("id"));
            gameType.setNumOfDice(JsonHelper.getInt(gameObject, "numOfDice"));
            gameType.setNumOfRolls(JsonHelper.getInt(gameObject, "numOfRolls"));
            gameType.setResultRow(JsonHelper.getInt(gameObject, "resultRow"));
            gameType.setTitle(JsonHelper.parseLocalizedKey(gameObject, "title"));
            gameType.setDefaultVariant(JsonHelper.getInt(gameObject, "defaultVariant"));
            gameType.setVariantValues((String) gameObject.get("variants"));
            gameType.setVersionDate((String) gameObject.get("versionDate"));
            gameType.setVersionName((String) gameObject.get("versionName"));

            LinkedList<GameRow> rows = new LinkedList<GameRow>();
            JSONArray rowsArray = (JSONArray) gameObject.get("rows");

            for (int j = 0; j < rowsArray.size(); j++) {
                JSONObject rowObject = (JSONObject) rowsArray.get(j);
                GameRow gameRow = new GameRow();
                gameRow.setId((String) rowObject.get("id"));
                gameRow.setTitle(JsonHelper.parseLocalizedKey(rowObject, "title"));
                gameRow.setTitleSymbol(JsonHelper.optString(rowObject, "titleSym"));
                gameRow.setLim(JsonHelper.optInt(rowObject, "lim"));
                gameRow.setMax(JsonHelper.optInt(rowObject, "max"));
                gameRow.setSum(JsonHelper.optBoolean(rowObject, "isSum"));
                gameRow.setBonus(JsonHelper.optBoolean(rowObject, "isBonus"));
                gameRow.setRollCounter(JsonHelper.optBoolean(rowObject, "isRollCounter"));
                gameRow.setPlayable(JsonHelper.optBoolean(rowObject, "isPlayable"));

                if (rowObject.containsKey("sum")) {
                    gameRow.setSumSet((String) rowObject.get("sum"));

                }
                rows.add(gameRow);
            }

            gameType.setRows(rows);
            mGameTypes.add(gameType);
        }

        Collections.sort(mGameTypes, GameType.NameComparator);
    }
}
