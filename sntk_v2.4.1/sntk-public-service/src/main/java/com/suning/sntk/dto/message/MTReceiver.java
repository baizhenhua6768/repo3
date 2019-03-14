package com.suning.sntk.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MTReceiver")
public class MTReceiver {

    @XStreamAlias("ReceiverType")
    private String receiverType = "3";
    @XStreamAlias("Receiver")
    private String receiver;
    @XStreamAlias("RecvChannel")
    private String recvChannel = "1";

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRecvChannel() {
        return recvChannel;
    }

    public void setRecvChannel(String recvChannel) {
        this.recvChannel = recvChannel;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"receiverType\":\"");
        builder.append(receiverType);
        builder.append("\",\"receiver\":\"");
        builder.append(receiver);
        builder.append("\",\"recvChannel\":\"");
        builder.append(recvChannel);
        builder.append("\"}");
        return builder.toString();
    }

}