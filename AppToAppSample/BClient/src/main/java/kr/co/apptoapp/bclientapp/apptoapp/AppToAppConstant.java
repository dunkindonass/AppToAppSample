package kr.co.apptoapp.bclientapp.apptoapp;

/**
 * define request code between clients
 */
public interface AppToAppConstant {

    // AClient to BClicnt Request Code
    static final int ACLIENT_REQUEST_ADD = 1;
    static final int ACLIENT_REQUEST_FORCEEXIT = 3;
    static final int ACLIENT_SEND_TIMESTAMP = 2;
    static final int DISCONNETCLIENTA = 6;

    // BClient to AClicnt Request Code
    static final int ACLIENT_RESPONSE_ADD = 1;
    static final int BCLIENT_SEND_TIMESTAMP = 4;

}
