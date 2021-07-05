package com.turan.merhabadunya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class MerhabadunyaApplication {

	private HumanModel humanModel = new HumanModel("ismail",31,"turan");
	public static void main(String[] args) {

		SpringApplication.run(MerhabadunyaApplication.class, args);
	}

	@GetMapping(path = "/baba")
	public List<String> donder(@RequestParam int nededin){
		return List.of("salamunaleykum", "ikindi-i serifleriniz", "hayirli", "olsun:"+nededin);
	}

	@GetMapping(path = "/dede")
	public String/*HumanModel*/ gonder(){
		return  humanModel.toString();

	}

}
