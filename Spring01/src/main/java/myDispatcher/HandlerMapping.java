package myDispatcher;

import java.util.HashMap;
import java.util.Map;

// ** 요청에 대한 ServiceController 를 생성 후 제공 
// => sington 패턴 
// => 요청명 대 ServiceController 는 Map 으로 처리 

public class HandlerMapping {
	// Map 정의 
	private Map<String,MyController> mappings;
	
	// 싱글톤 패턴 
	// 생성자를 private 으로,메서드를 이용하여 생성해서 전달 ->  getInstance(); 
	
	private static HandlerMapping instance = new HandlerMapping();
	
	public static HandlerMapping getInstance() {
		// return new HandlerMapping();
		// => 메서드 호출때 마다 새로운 instance 만들어짐 (싱글톤의 위배) 
		
		return instance ;
	}
	
	// 생서자에서 Map 초기화 
	private HandlerMapping() {
		mappings = new HashMap<String,MyController>();
		mappings.put("/mlist",new C01_MList());
		mappings.put("/mdetail",new C02_MDetail());
	}
	
	public MyController getController(String mappingName){
		return mappings.get(mappingName);
	}
	
}//class
