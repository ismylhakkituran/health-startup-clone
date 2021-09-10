package com.turan.service;

import com.zaxxer.hikari.util.ClockSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Service
public class SlackService {

    @Value("properties.uri.web")
    public String deneme;
    @Value("${properties.uri.web}")
    public String baseUri="https://spermatorial-dev.herokuapp.com";
    @Value("${properties.notification.webhook}")
    public String webhook="https://hooks.slack.com/services/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";
    //gemini-prod-slack public String webhook="https://hooks.slack.com/services/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";

    private final RestTemplate restTemplate;

    public SlackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void send(String message) {
        System.out.println("Slack.Service send:");
        System.out.println("baseUri: " + baseUri);
        System.out.println("webhook: " +webhook);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("username", "GeminiLab Bot");
        body.put("icon_url", "https://geminilab.co/assets/favicon/apple-touch-icon.png");
        body.put("blocks", message);

        ResponseEntity<String> temp = restTemplate.exchange(
                webhook, HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
    }

    public String newAppointmentMessage(String myID) {
        String temp= """
                [
                    {
                        "type": "divider"
                    },
                    {
                        "type": "section",
                        "text": {
                            "type": "mrkdwn",
                            "text": "Randevu Siparişi Geldi! <%s/admin/zuruzuru/%s|%s> %s :stethoscope:"
                        }
                    },
                    {
                        "type": "divider"
                    }
                ]
                """.formatted( baseUri, myID , myID, ClockSource.currentTime()  );
        send(temp);
        return temp;
    }

    public String newKitMessage(String myID) {
        String temp= """
                [
                    
                    {
                        "type": "section",
                        "text": {
                            "type": "mrkdwn",
                            "text": "DENEME DENEME Kit Siparişi Geldi! <%s/admin/orders/%s|%s>"
                        }
                        
                    }
                ]
                 """.formatted( baseUri , myID, myID );
                //""".formatted(delivery.getId() );
        send(temp);
        return temp;
    }

    public String orderMessage(String myID) {
        String temp= """
                [
                    {
                        "type": "section",
                        "text": {
                            "type": "plain_text",
                            "emoji": true,
                            "text": "DENEME DENEME :"
                        }
                    },
                    {
                        "type": "section",
                        "text": {
                            "type": "mrkdwn",
                            "text": "*<%s|Id: %s>*\\n%s"
                        },
                        "accessory": {
                            "type": "image",
                            "image_url": "https://geminilab.co/assets/images/kit.png",
                            "alt_text": "kit"
                        }
                    },
                    {
                        "type": "divider"
                    }
                ]
                """.formatted(
                baseUri + "/admin/orders/" + myID,
                myID, "00/00/00" );
        send(temp);
        return "foo ::: "+temp;
    }
/*
    public String deliveryMessage(Delivery delivery) {
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
                            "text": "Kurye Talebi Geldi! -%s-"
                        }
                    },
                    {
                        "type": "divider"
                    }
                ]
                """.formatted(delivery.getId());
    }

    public String labFilledReportMessage(KitProduct product) {
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
                            "text": "Rapor Güncellemesi! -%s-"
                        }
                    },
                    {
                        "type": "divider"
                    }
                ]
                """.formatted(product.getId());
    }*/
}
