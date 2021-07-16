package myDispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ** Spring MVC2_ver 01 
// => MyDispatherServlet
// => MyDispatcherServlet (FrontController 역할)
// 	  HandlerMapping, ViewResolver 를 활용해서 
//    Service , view 를 처리 

public class MyDispatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private HandlerMapping hmappings ;
    private ViewResolver vresolver; 
	// => viewName 앞의 경로, 뒤의 확장자를 처리해줌 
    
	public MyDispatherServlet() {
        super();
        hmappings = HandlerMapping.getInstance();
        vresolver = new ViewResolver();
        vresolver.setprefix("/WEB-INF/views/");
        vresolver.setsuffix(".jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청(request) 분석
    	// => url 또는 uri 분석해서 요청명 확인
    	// => 한글처리 
    	request.setCharacterEncoding("utf-8");
    	String uri = request.getRequestURI();
    	String mappingName = uri.substring(uri.lastIndexOf("/"));
    	String viewName = "home" ;
	
    	// 2. 해당 서비스 실행
    	// => 공장 (HandlerMapping) 에 요청, 해당되는 컨트롤러를 전달받음 
    	// => 해당 서비스 메서드 실행 
    	
    	MyController service = hmappings.getController(mappingName);
    	if ( service!=null) {
    		viewName = service.handleRequest(request, response);
    	}else request.setAttribute("message", "~~ 없는 요청 입니다 ~~") ;
    	
    	// 3. View 처리
    	viewName=vresolver.getViewName(viewName); 
    	request.getRequestDispatcher(viewName).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
