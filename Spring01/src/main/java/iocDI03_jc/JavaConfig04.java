package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ** test4) xml 을 통해서 JC 사용
// => xml 에서 JC 화일을 import 적용 

@Configuration
public class JavaConfig04 {

	//** Test2) 생성자를 통한 Speaker 주입
	@Bean
	public TV lgtv() {
		return new LgTVsi(spA(),12345000,"Pink");
	} // for_getBean 

	public Speakeri spA() {
		return new SpeakerA();
	}
	
	// ** Test3)
	// => @Autowired 를 위해 @Qualifier("spB") 에 전달해야 하므로 
	//    @Bean 이 필요함. (적용 전,후 오류메시지를 확인해본다)
//	@Bean
//	public Speakeri spB() {
//		return new SpeakerB();
//	}
	// => @Autowired 를 위한 SpeakerB 의 생성을 xml 로 변경해본다
	
	@Bean
	public TV aitv() {
		return new AiTVsi(); // @Autowired Test 
	}

} //class
