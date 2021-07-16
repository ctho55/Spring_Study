package myDispatcher;

public class ViewResolver {

	private String prefix;
	private String suffix;
	
	public void setprefix(String prefix) {
		this.prefix=prefix;
	}
	
	public void setsuffix(String suffix) {
		this.suffix=suffix;
	}
	
	public String getViewName(String viewName) {
		viewName = prefix+viewName+suffix;
		System.out.println("viewName=> "+viewName);
		
		return viewName;
	}
	
	
}//class
