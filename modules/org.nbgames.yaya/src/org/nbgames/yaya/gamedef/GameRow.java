package org.nbgames.yaya.gamedef;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GameRow {
	private String mId;
	private String mTitle;
	private String mTitleSymbol;

	public String dump() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getId()).append("\n");
		stringBuilder.append(getTitle()).append("\n");
		stringBuilder.append(getTitleSymbol()).append("\n");

		return stringBuilder.toString();
	}

	public String getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getTitleSymbol() {
		return mTitleSymbol;
	}

	public void setId(String id) {
		mId = id;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public void setTitleSymbol(String titleSymbol) {
		mTitleSymbol = titleSymbol;
	}

}
