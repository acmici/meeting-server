package com.acmici.meeting;

import sun.net.ftp.FtpProtocolException;

import java.io.IOException;

/**
 * Created by canhe on 2015/4/11.
 */
public interface MeetingClient {
    public void connectServer() throws IOException, FtpProtocolException;

    public void getFiles(String dir, String localDir) throws IOException, FtpProtocolException;
}
