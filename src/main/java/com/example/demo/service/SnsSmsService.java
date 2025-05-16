package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.AuthorizationErrorException;
import software.amazon.awssdk.services.sns.model.FilterPolicyLimitExceededException;
import software.amazon.awssdk.services.sns.model.InternalErrorException;
import software.amazon.awssdk.services.sns.model.InvalidParameterException;
import software.amazon.awssdk.services.sns.model.InvalidSecurityException;
import software.amazon.awssdk.services.sns.model.NotFoundException;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.ReplayLimitExceededException;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import software.amazon.awssdk.services.sns.model.SubscriptionLimitExceededException;

@Slf4j
@Service
public class SnsSmsService {
	
	@Autowired
	private SnsClient snsClient;
	
	public String sendSms(String phoneNumber, String message) {
	    PublishResponse result;
		try {
	    	log.info(":::::Message service started:::::");
			PublishRequest request = PublishRequest.builder()
			        .message(message)
			        .phoneNumber(phoneNumber) 
			        .build();

			result = snsClient.publish(request);
        	log.info(":::::Message service End:::::");
            return "Message published to SNS. MessageId: " + result.messageId();
            
		} catch (SnsException e) {
			e.printStackTrace();
        	log.info(":::::Error Occured in sendSms() function due to :{}",e.getMessage());
            return "Failed to publish message: " + e.awsErrorDetails().errorMessage();
		} 
	}
	
}
