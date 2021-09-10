package com.turan.merhabadunya;

import com.turan.configuration.WholeConfigs;
import com.turan.service.SlackService;
import com.turan.configuration.WholeConfigs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@SpringBootApplication
public class MerhabadunyaApplication {

	private HumanModel humanModel = new HumanModel("ismail",31,"turan");

	private final String myID = "60df079ec75425009109eb2a";
	private final RestTemplate restTemplate = new RestTemplate();
	private final SlackService slackService = new SlackService(restTemplate);
	private final WholeConfigs wholeConfigs = new WholeConfigs();
	public static void main(String[] args) {

/*		Logger logger = LogManager.getLogger();
			logger.debug("This is a debug message");
			logger.info("This is an info message");
			logger.warn("This is a warn message");
			logger.error("This is an error message");
			//logger.fatal("This is a fatal message");*/

		SpringApplication.run(MerhabadunyaApplication.class, args);
	}

	@GetMapping(path = "/baba")
	public List<String> donder(@RequestParam int nededin){

		//final String s1 = slackService.newKitMessage(myID);
		final String s1 = slackService.orderMessage(myID);
		System.out.println("slackService.orderMessage(myID) LOGGER: "+s1);

		//System.out.println("slackService.newKitMessage(myID) LOGGER: "+s1);

/*
		final String s2 = slackService.newAppointmentMessage(myID);
		System.out.println("slackService.newAppointmentMessage(myID) LOGGER: "+s2);
*/
		return List.of("salamunaleykum", "ikindi-i serifleriniz", "hayirli", "olsun:"+nededin);
	}



	@GetMapping(path = "/dede")
	public String/*HumanModel*/ gonder(){



		return  wholeConfigs.webb +"< "+slackService.baseUri+" "+slackService.webhook+" " + humanModel.toString();

	}
	/*
	public final String myID = "60df079ec75425009109eb2a";

	@Value("${properties.uri.web}")
	private String baseUri;

	public String newKitMessage() {
		return """
                [
                    {
                        "type": "divider"
                    },
                    {
                        "type": "section",
                        "text": {
                            "type": "plain_text",
                            "emoji": true,
                            "text": "Kit Siparişi Geldi! -%s-"
                        }
                    },
                    {
                        "type": "divider"
                    }
                ]
                """.formatted(  baseUri +"/"+ myID );
	}*/

}
