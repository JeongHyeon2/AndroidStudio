package com.example.managementapp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface Network {
    Socket[] socket = new Socket[1];
    OutputStream[] os = new OutputStream[1];
    InputStream[] is = new InputStream[1];

}
