package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java bean configuration class를 이용한 DI
// => test03 : 스피커 2개중 선택 
// => 생성자 를 이용한 주입..

// *** JC 와 @ 병행사용
// *** JC , @, xml 병행사용
// => JC 내에서 xml 로 생성된 객체를 직접 사용하는것은 허용 되지 않음. 
//    단, User 클래스에서는 사용가능

@ImportResource("iocDI03_jc/app03.xml")
@Configuration
public class JavaConfig03 {
	
	// ** Test1) 고전적방법 : 직접 new
	// => xml 에서 생성 하고 getBean("..") 하도록 수정 해본다.
//	@Bean
//	// =>생성된 Bean을 컨테이너에 전딜하기위한 경우에만 사용 
//	public TV sstv() {
//		return new SsTVsi();
//	} // for_getBean 
	
	//** Test2) 생성자를 통한 Speaker 주입
	// => Speaker생성 : 컨테이너에 전딜하기위한 용도가 아니므로 생성 메서드만 작성함
	// => LgTVs @Bean설정
	@Bean
	public TV lgtv() {
		return new LgTVsi(spA(),12345000,"Pink");
	} // for_getBean 
	
	public Speakeri spA() {
		return new SpeakerA();
	}
	
	// ** Test3)
	// => JC 와 @ 병행사용
	// => xml 설정 없이 @Autowired,  이용한 자동 주입이 가능함
	
	// => @Autowired 를 위해 @Qualifier("spB") 에 전달해야 하므로  
	//    @Bean 이 필요함. (적용 전,후 오류메시지를 확인해본다)
	@Bean
	public Speakeri spB() {
		return new SpeakerB();
	}
	@Bean
	public TV aitv() {
		//return new AiTVsi(spB()); // 생성자주입
		return new AiTVsi(); // @Autowired Test 
	}
	
} //class
