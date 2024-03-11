package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;


@Component  //chef와 같이 bean이 되어야 의존성 주입 성립
@Data
public class Restaurant {
	//의존성 주입 : Restaurant는 Chef에 의존
	
	//수정자 주입(롬복)
	@Setter(onMethod_=@Autowired)
	private Chef chef;
	
	//수정자 주입
//	@Autowired
//	public void setChef(Chef chef) {
//		this.chef = chef;
//	}
	
	//생성자 주입(묵시적) ---권장
//	public Restaurant(Chef chef) {
//		this.chef = chef;
//	}
}
