package jxnu.n433.x3107.bean;

public class LocatorAnDSeekSQL {

	public String Seek;//搜索记录
	public String Locator;//定位输入记录
	private String id ="";
	
	public String getId() {
		return id;
	}
	
	public LocatorAnDSeekSQL setId(String id) {
		this.id = id;
		return this;
	}
	
	public LocatorAnDSeekSQL setSeek(String seek) {
		this.Seek = seek;
		return this;
	}
	
	public String getSeek() {
		return Seek;
	}
	
	public LocatorAnDSeekSQL setLocator(String locator) {
		this.Locator = locator;
		return this;
	}
	public String getLocator() {
		return Locator;
	}
	
}
