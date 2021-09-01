package vo;

import java.util.Date;

// ** Login 정보 보관하기 
public class UserVO {
	private String id;
	private String name;
	private String lev;
	private String sctime;   // Session Creation Time
	
	// sctime 을 Date Type 으로 처리하는 경우
	private Date sctimeDate; 
	public Date getSctimeDate() {
		return sctimeDate;  	}
	public void setSctimeDate(Date sctimeDate) {
		this.sctimeDate = sctimeDate; 	}
	//------------------------------
	
	public String getId() {	return id;	}
	public void setId(String id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;	}
	public String getLev() {return lev;	}
	public void setLev(String lev) {this.lev = lev;}
	public String getSctime() {return sctime;}
	public void setSctime(String sctime) {this.sctime = sctime;}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", lev=" + lev + ", sctime=" + sctime + "]";
	}
} //class
