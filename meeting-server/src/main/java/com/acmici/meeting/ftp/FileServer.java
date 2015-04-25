package com.acmici.meeting.ftp;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

import java.io.File;

/**
 * Created by canhe on 2015/4/11.
 */
public class FileServer {
    private FtpServerFactory serverFactory;
    private ListenerFactory listenerFactory;
    private PropertiesUserManagerFactory userManagerFactory;
    private UserManager userManager;
    private String home;
    private FtpServer server;

    public FileServer() throws FtpException {
        serverFactory = new FtpServerFactory();
        listenerFactory = new ListenerFactory();
        userManagerFactory = new PropertiesUserManagerFactory();
    }

    public void startServer() throws FtpException {
        listenerFactory.setPort(2221);
        serverFactory.addListener("default", listenerFactory.createListener());
        //userManagerFactory.setFile(new File("D:\\jjl\\meeting-server\\users.properties"));
        userManager = userManagerFactory.createUserManager();
        BaseUser user = new BaseUser();
        user.setName("anonymous");
        user.setHomeDirectory(home);
        userManager.save(user);
        serverFactory.setUserManager(userManager);
        server = serverFactory.createServer();
        server.start();
    }

    public void setHome(String path) {
        if (path != null) {
            home = path;
        } else {
            home = "C:";
        }
    }

    //don't use
    public void restartServer() throws FtpException {
        server.stop();
        while (!server.isStopped());
        startServer();
    }

    public static void main(String[] args) {
        FileServer myServer;
        try {
            myServer = new FileServer();
            myServer.setHome("D:\\jjl\\meeting-system\\meeting-system\\meeting-server\\meeting_files");
            myServer.startServer();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }
}
