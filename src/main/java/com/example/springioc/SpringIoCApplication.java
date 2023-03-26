package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class SpringIoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIoCApplication.class, args);
		//spring application이 실행된 후 적용될 내용
		ApplicationContext context = ApplicationContextProvider.getContext();

		//url
		String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

		//new Constructor가 아닌 context객체에서 주입받음
		Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
	//	UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
		Encoder encoder = context.getBean(Encoder.class);

		String result = encoder.encode(url);
		System.out.println(result);

		encoder = context.getBean("urlEncode1",Encoder.class);
		result = encoder.encode(url);
		System.out.println(result);
	}

}

@Configuration
class AppConfig{
	//Configuration도 Component와 같은 역할을 하지만
	// Bean을 직접 여러개를 적용할수 있다는 장점이 있다
	@Bean("base64Encode")
	//변경한 이름(base64Encoder)은 Encoder class에서 사용하고 있기때문에 곂치지 않는다.
	public Encoder encoder(Base64Encoder base64Encoder){
		return new Encoder(base64Encoder);
	}
	@Bean("urlEncode1")
	public Encoder encoder(UrlEncoder urlEncoder){
		return new Encoder(urlEncoder);
	}
}