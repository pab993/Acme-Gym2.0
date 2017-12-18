
package forms;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class SearchTemplateForm {

	private String	keyword;
	private String	day;
	private String	time;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
