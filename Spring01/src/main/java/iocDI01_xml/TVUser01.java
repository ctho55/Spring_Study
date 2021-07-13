package iocDI01_xml;

// ** 1. 절차지향 
class SsTV {
	public void turnon() {
		System.out.println("~~Sstv turn on~~");
	}
	public void turnoff() {
		System.out.println("~~Sstv turnoff~~");
	}
	public void soundUp() {
		System.out.println("~~Sstv soundUp~~");
	}
	public void soundDown() {
		System.out.println("~~Sstv soundDown~~");
	}
}// Sstv

class LgTV {
	public void poweron() {
		System.out.println("~~LgTv power on~~");
	}
	public void poweroff() {
		System.out.println("~~LgTv power off~~");
	}
	public void volumeUp() {
		System.out.println("~~LgTv volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~LgTv volumeDown~~");
	}
}// Lgtv



// ** Test2. 객체지향 : 다형성적용 
interface TV{
	public void poweron();
	public void poweroff();
	public void volumeUp();
	public void volumeDown();
}

class SsTVi implements TV {
	public SsTVi(){System.out.println("~~SsTVi 생성자!~~");}
	public void poweron() {
		System.out.println("~~SsTVi power on~~");
	}
	public void poweroff() {
		System.out.println("~~SsTVi power off~~");
	}
	public void volumeUp() {
		System.out.println("~~SsTVi volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~SsTVi volumeDown~~");
	}
}
class LgTVi implements TV {
	public LgTVi(){System.out.println("~~LgTVi 생성자!~~");}
	public void poweron() {
		System.out.println("~~LgTVi power on~~");
	}
	public void poweroff() {
		System.out.println("~~LgTVi power off~~");
	}
	public void volumeUp() {
		System.out.println("~~LgTVi volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~LgTVi volumeDown~~");
	}
}

//Test3. Factory 패턴적용 (IOC/DI 입문)
class BeanFactory {
	
	public TV getBean(String req) {
		if(req.equals("ss")) return new SsTVi();
		else if (req.equals("lg")) return new LgTVi();
		else return null ;
	}
}



public class TVUser01 {

	public static void main(String[] args) {
		// Test1. 절차지향
	    // => tv 교체가 필요
	    //    완전 재코딩
//		SsTV tv = new SsTV();
//		tv.turnon();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnoff();
		
		// => LgTV로 교체 
		LgTV tv = new LgTV();
		tv.poweron();
		tv.volumeUp();
		tv.volumeDown();
		tv.poweroff();	
		
		// Test2. 객체지향 : 다형성적용
		// => 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌
		// => tv 교체가 필요
		// 우측의 클래스만 교체
		// => 단, 소스코드의 수정이 필요함 
		System.out.println("** Test2. 객체지향 **");
		TV tvi = new SsTVi();  //new LgTV(), 변경이 손쉽다.
		tvi.poweron();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.poweroff();	
		
		// Test3. Factory 패턴적용 (IOC/DI 입문)
	    // => 실행시에 코드 수정없이 클래스(TV) 교체 
		// => user 의 요구사항(필요한 클래스) Factory에 전달하는 방법
	    // => xml, @, JavaCode (JavaConfig)
		
		
		System.out.println("** Test3. Factory 패턴적용 **");
		BeanFactory bf = new BeanFactory();
//		TV tvf = bf.getBean(args[0]); // 실시간으로 소스코드 수정없이 요청전달
		TV tvf = bf.getBean("ss");
		if (tvf !=null) {
			tvf.poweron();
			tvf.volumeUp();
			tvf.volumeDown();
			tvf.poweroff();
		}else System.out.println("TV 선택 오류 ~");
		
	}//main
}// class 
