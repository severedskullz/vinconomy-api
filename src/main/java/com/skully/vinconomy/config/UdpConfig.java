package com.skully.vinconomy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.MessageHandler;


@Configuration
public class UdpConfig {

    @Bean
    public UnicastSendingMessageHandler udpOutboundAdapter() {
        return new UnicastSendingMessageHandler("localhost", 12345); // Replace with target host and port
    }

    @Bean
    @ServiceActivator(inputChannel = "udpOutboundChannel")
    public MessageHandler udpOutboundMessageHandler() {
        return message -> {
            byte[] payload = (byte[]) message.getPayload();
            // Send the UDP packet using the UnicastSendingMessageHandler
            udpOutboundAdapter().handleMessage(message);
        };
    }
}