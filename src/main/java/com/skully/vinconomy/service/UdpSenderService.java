package com.skully.vinconomy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class UdpSenderService {

    @Autowired
    private MessageChannel udpOutboundChannel;

    public void sendUdpMessage(String message) {
        Message<byte[]> udpMessage = MessageBuilder.withPayload(message.getBytes()).build();
        udpOutboundChannel.send(udpMessage);
    }
}
