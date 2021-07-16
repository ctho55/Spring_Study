package vo;

// ** VO (Value Object)
// => setter, getter, toString
public class LoginVO {
	private String id ; 
	private String name ; 
	private String lev ; 
	private String regdate ;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	@Override
	public String toString() {
		return  id + ","+ name + ","+ lev +","+ regdate ;
	} 
	
	
	
	
	
} //class
