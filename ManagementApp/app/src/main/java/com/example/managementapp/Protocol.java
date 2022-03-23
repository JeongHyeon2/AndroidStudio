package com.example.managementapp;

public class Protocol {
    public static final int PT_UNDEFINED = -1;	// 프로토콜이 지정되어 있지 않은 경우
    public static final int REQ_ADD= 1;	// 회원 등록
    public static final int REQ_DEL= 2;// 회원 삭제
    public static final int LEN_MAX = 1000;		//최대 데이터 길이
    public static final int LEN_PROTOCOL_TYPE=1;
    protected int protocolType;
    private byte[] packet;

    public Protocol(){					// 생성자
        this(PT_UNDEFINED);
    }

    public Protocol(int protocolType){	// 생성자
        this.protocolType = protocolType;
        getPacket(protocolType);
    }
    public byte[] getPacket(int protocolType){
        if(packet==null){
            packet = new byte[LEN_MAX];
        }
        packet[0] = (byte)protocolType;	// packet 바이트 배열의 첫 번째 바이트에 프로토콜 타입 설정
        return packet;
    }

    public void setProtocolType(int protocolType){
        this.protocolType = protocolType;
    }

    public void setPacket(int pt, byte[] buf){
        packet = null;
        packet = getPacket(pt);
        protocolType = pt;
        System.arraycopy(buf, 0, packet, 0, packet.length);
    }
    public int getProtocolType(){
        return protocolType;
    }

    public byte[] getPacket(){
        return packet;
    }
    public void setData(String data){
        System.arraycopy(data.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, data.trim().getBytes().length);
    }
    public String getData(){
        String dataLine = new String(packet, 1,LEN_MAX).trim();
        return dataLine;
    }

}
