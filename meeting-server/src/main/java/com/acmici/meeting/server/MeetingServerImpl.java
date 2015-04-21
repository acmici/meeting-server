package com.acmici.meeting.server;

import com.acmici.meeting.ftp.FileServer;
import org.apache.ftpserver.ftplet.FtpException;

/**
 * Created by canhe on 2015/4/12.
 */
public class MeetingServerImpl implements MeetingServer {
    private FileServer fileServer;
    private String topic;
    private String members;
    private String recorder;
    private String file_path;

    public MeetingServerImpl(String topic, String members, String recorder, String file_path) {
        this.topic = topic;
        this.members = members;
        this.recorder = recorder;
        this.file_path = file_path;
    }

    @Override
    public void startServer() throws FtpException {
        fileServer = new FileServer();
        fileServer.setHome(file_path);
        fileServer.startServer();
    }

    @Override
    public void setHome(String home) throws FtpException {
        fileServer.setHome(home);
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getMembers() {
        return members;
    }

    @Override
    public void setMembers(String members) {
        this.members = members;
    }

    @Override
    public String getRecorder() {
        return recorder;
    }

    @Override
    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    @Override
    public String getFile_path() {
        return file_path;
    }

    @Override
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
