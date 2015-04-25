package com.acmici.meeting;

import sun.net.ftp.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Iterator;

/**
 * Created by canhe on 2015/4/11.
 */
public class MeetingClientImpl implements MeetingClient {
    FtpClient client;

    public MeetingClientImpl() throws IOException, FtpProtocolException {
        client = FtpClient.create();
    }

    @Override
    public void connectServer() throws IOException, FtpProtocolException {
        client.connect(new InetSocketAddress(2221));
        FtpReplyCode rep = client.getLastReplyCode();
//        System.out.println(rep);
    }

    @Override
    public void getFiles(String absRemoteDir, String absLocalDir) throws IOException, FtpProtocolException {
        if (!client.isLoggedIn()) {
            client.login("anonymous", "aa".toCharArray());
        }
        client.setBinaryType();

        Iterator<FtpDirEntry> files = client.listFiles(absRemoteDir);
        while (files.hasNext()) {
            FtpDirEntry file = files.next();
            FtpDirEntry.Type type = file.getType();

            if (type == FtpDirEntry.Type.DIR) {
                File f = new File(absLocalDir + "\\" + file.getName());
                System.out.println(f.toString());
                if (!f.exists() && !f.isDirectory()) {
                    f.mkdirs();
                }
                getFiles(absRemoteDir + file.getName() + "/", absLocalDir + file.getName() + "\\");
            } else if (type == FtpDirEntry.Type.FILE) {
                String fileName = file.getName();

                FileOutputStream output = new FileOutputStream(absLocalDir + fileName);
                client.getFile(absRemoteDir + fileName, output);

                System.out.println("retrieveFile " + fileName + ";\nreply code: " + client.getLastResponseString());
                output.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
//            System.setProperty("sun.jnu.encoding","utf-8");
            MeetingClient myClient = new MeetingClientImpl();
            myClient.connectServer();
            myClient.getFiles("/", "D:\\jjl\\meeting-system\\");
            System.out.println(System.getProperties());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        }
    }
}
