package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
public class Encoder {
	//내부에 인터페이스 객체를 얻고 생성자에서 인스턴스를 관리
	private IEncoder iEncoder;
	
	public Encoder(@Qualifier("renameBase64Encoder") IEncoder iEncoder) {
//지금처럼 Component가 여러개인 경우 선택지가 중복되면 에러 발생 > Qualifier annotation 사용
//component에 이름을 지정한 경우 그이름을, 없는경우 class 이름의 제일 앞만 소문자로 바꿔서 지정 가능
		this.iEncoder = iEncoder;
	}
/*	public Encoder(IEncoder)의 같은 형태는 불가.
	public Encoder(@Qualifier("urlEncoder") IEncoder iEncoder){
		this.iEncoder = iEncoder;
	}
*/
	public String encode(String message) {
		return iEncoder.encode(message);
		//return Base64.getEncoder().encodeToString(message.getBytes());
	}

	public void setiEncoder(IEncoder iEncoder){
		this.iEncoder = iEncoder;
	}
}
