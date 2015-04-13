package com.acmici.meeting.server;

import org.apache.ftpserver.ftplet.FtpException;

/**
 * Created by canhe on 2015/4/11.
 */
public interface MeetingServer {
    public void startServer() throws FtpException;

    public String getTopic();

    public void setTopic(String topic);

    public String getMembers();

    public void setMembers(String members);

    public String getRecorder();

    public void setRecorder(String recorder);

    public String getFile_path();

    public void setFile_path(String file_path);

    public void setHome(String home) throws FtpException;
}
