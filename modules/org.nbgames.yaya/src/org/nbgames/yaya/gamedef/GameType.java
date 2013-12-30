package org.nbgames.yaya.gamedef;

import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GameType {

    public static Comparator<GameType> NameComparator = new Comparator<GameType>() {

        @Override
        public int compare(GameType type1, GameType type2) {

            return type1.getTitle().compareTo(type2.getTitle());
        }

    };
    public static final String VARIANT_ASCENDING = "ascending";
    public static final String VARIANT_DESCENDING = "descending";
    public static final String VARIANT_LOWER_UPPER = "lower_upper";
    public static final String VARIANT_RANDOM = "random";
    public static final String VARIANT_STANDARD = "standard";
    public static final String VARIANT_UPPER_LOWER = "upper_lower";
    private String mAuthor;
    private int mDefaultVariant;
    private String mId;
    private int mNumOfDice;
    private int mNumOfRolls;
    private int mResultRow;
    private LinkedList<GameRow> mRows = new LinkedList<GameRow>();
    private String mTitle;
    private String[] mVariantValues;
    private String mVersionDate;
    private String mVersionName;

//    public static String getEntry(String key) {
//        int entryKey = R.string.txn__error;
//
//        if (key.equalsIgnoreCase(VARIANT_STANDARD)) {
//            entryKey = R.string.settings_game_variant_standard;
//        } else if (key.equalsIgnoreCase(VARIANT_DESCENDING)) {
//            entryKey = R.string.settings_game_variant_descending;
//        } else if (key.equalsIgnoreCase(VARIANT_ASCENDING)) {
//            entryKey = R.string.settings_game_variant_ascending;
//        } else if (key.equalsIgnoreCase(VARIANT_UPPER_LOWER)) {
//            entryKey = R.string.settings_game_variant_upper_lower;
//        } else if (key.equalsIgnoreCase(VARIANT_LOWER_UPPER)) {
//            entryKey = R.string.settings_game_variant_lower_upper;
//        } else if (key.equalsIgnoreCase(VARIANT_RANDOM)) {
//            entryKey = R.string.settings_game_variant_random;
//        }
//
//        return context.getString(entryKey);
//    }
    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getId()).append("\n");
        stringBuilder.append(getTitle()).append("\n");
        stringBuilder.append(getAuthor()).append("\n");
        stringBuilder.append(getDefaultVariant()).append("\n");
        stringBuilder.append(getVersionDate()).append("\n");
        stringBuilder.append(getVersionName()).append("\n");
        stringBuilder.append("resultRow " + getResultRow()).append("\n");
        stringBuilder.append("numOfDice " + getNumOfDice()).append("\n");
        stringBuilder.append("numOfRolls " + getNumOfRolls()).append("\n");

        for (GameRow row : getRows()) {
            stringBuilder.append(row.dump()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String getAuthor() {
        return mAuthor;
    }

    public int getDefaultVariant() {
        return mDefaultVariant;
    }

    public String getId() {
        return mId;
    }

    public int getNumOfDice() {
        return mNumOfDice;
    }

    public int getNumOfRolls() {
        return mNumOfRolls;
    }

    public int getResultRow() {
        return mResultRow;
    }

    public LinkedList<GameRow> getRows() {
        return mRows;
    }

    public String getTitle() {
        return mTitle;
    }

//    public String[] getVariantEntries() {
//        String[] entries = new String[mVariantValues.length];
//
//        for (int i = 0; i < mVariantValues.length; i++) {
//			// int entryKey = R.string.txn__default;
//            // if (mVariantValues[i].equalsIgnoreCase(VARIANT_STANDARD)) {
//            // entryKey = R.string.settings_game_variant_standard;
//            // } else if (mVariantValues[i].equalsIgnoreCase(VARIANT_DESCENDING)) {
//            // entryKey = R.string.settings_game_variant_descending;
//            // } else if (mVariantValues[i].equalsIgnoreCase(VARIANT_ASCENDING)) {
//            // entryKey = R.string.settings_game_variant_ascending;
//            // } else if (mVariantValues[i].equalsIgnoreCase(VARIANT_UPPER_LOWER)) {
//            // entryKey = R.string.settings_game_variant_upper_lower;
//            // } else if (mVariantValues[i].equalsIgnoreCase(VARIANT_LOWER_UPPER)) {
//            // entryKey = R.string.settings_game_variant_lower_upper;
//            // } else if (mVariantValues[i].equalsIgnoreCase(VARIANT_RANDOM)) {
//            // entryKey = R.string.settings_game_variant_random;
//            // }
//            //
//            // entries[i] = context.getString(entryKey);
//            
//            entries[i] = getEntry(context, mVariantValues[i]);
//        }
//
//        return entries;
//    }
    public String[] getVariantValues() {
        return mVariantValues;
    }

    public String getVersionDate() {
        return mVersionDate;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setDefaultVariant(int defaultVariant) {
        mDefaultVariant = defaultVariant;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setNumOfDice(int numOfDice) {
        mNumOfDice = numOfDice;
    }

    public void setNumOfRolls(int numOfRolls) {
        mNumOfRolls = numOfRolls;
    }

    public void setResultRow(int resultRow) {
        mResultRow = resultRow;
    }

    public void setRows(LinkedList<GameRow> rows) {
        mRows = rows;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setVariantValues(String variants) {
        mVariantValues = variants.split(" ");
    }

    public void setVariants(String[] variants) {
        mVariantValues = variants;
    }

    public void setVersionDate(String versionDate) {
        mVersionDate = versionDate;
    }

    public void setVersionName(String versionName) {
        mVersionName = versionName;
    }
}
