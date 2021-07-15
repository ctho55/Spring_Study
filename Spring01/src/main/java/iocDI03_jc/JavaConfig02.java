package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java bean configuration class를 이용한 DI
// => test02 : 스피커 1개 사용 TV 
// => 생성자 를 이용한 주입

//** JC 에서 xml 병행 사용하기 
// => @ImportResource("iocDI03_jc/applicationContext.xml")
// => AiTVs 생성은 xml 로 

@ImportResource("iocDI03_jc/applicationContext.xml")
@Configuration
public class JavaConfig02 {
	
	// ** Test1) 고전적방법 : 직접 new
	@Bean
	// =>생성된 Bean을 컨테이너에 전딜하기위한 경우에만 사용 
	public TV sstv() {
		return new SsTVs();
	} // for_getBean 
	
	//** Test2) 생성자를 통한 Speaker 주입
	// => Speaker생성 : 컨테이너에 전딜하기위한 용도가 아니므로 생성 메서드만 작성함
	// => LgTVs @Bean설정
	@Bean
	public TV lgtv() {
		//return new LgTVs(sp(),12345000,"Pink");
		return new LgTVs(new Speaker(),12345000,"Pink");
	} // for_getBean 
	
//	public Speaker sp() {
//		return new Speaker();
//	}
	// ** Test3)
	// => xml 병행사용 Test
	//    xml 로 생성 한다.
//	public TV aitv() {
//		return new AiTVs();
//	}
	
} //class
