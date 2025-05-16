package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Slf4j
@Service
public class SnsMailService {
	
    @Autowired
    private SnsClient snsClient;
    
    public String publishMail(String subject, String message) {
        try {
        	log.info(":::::Message service started:::::");
            PublishRequest publishRequest = PublishRequest.builder()
                    .subject(subject)
                    .message(message)
                    .build();
            
            PublishResponse response = snsClient.publish(publishRequest);
        	log.info(":::::Message service End:::::");
            return "Message published to SNS. MessageId: " + response.messageId();
        } catch (SnsException e) {
            e.printStackTrace(); 
        	log.info(":::::Error Occured in publishMail() function due to :{}",e.getMessage());
            return "Failed to publish message: " + e.awsErrorDetails().errorMessage();
        }
    }
    
    
}
