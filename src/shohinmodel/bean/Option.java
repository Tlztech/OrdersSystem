package shohinmodel.bean;

import java.io.Serializable;

public class Option implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Select/Checkbox用項目名
	private String optionName;
	// 選択肢スタイル
	private int optionStyle;
	// Select/Checkbox用選択肢情報リスト
	private OptionValues optionValues;

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getOptionStyle() {
		return optionStyle;
	}

	public void setOptionStyle(int optionStyle) {
		this.optionStyle = optionStyle;
	}

	public OptionValues getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(OptionValues optionValues) {
		this.optionValues = optionValues;
	}

}
