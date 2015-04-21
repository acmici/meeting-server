package com.acmici.meeting;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;

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
//        if (!FtpClient.(rep)) {
//            client.disconnect();
//            System.err.println("FTP server refused connection.");
//        } else {
//            System.out.println("Connected to " + "localhost" + " on " + "port 2221");
//        }
        System.out.println(rep);
    }

    @Override
    public void getFiles(String dir, String localDir) throws IOException, FtpProtocolException {
        if (!client.isLoggedIn()) {
            client.login("anonymous", "aa".toCharArray());
        }
        client.setBinaryType();

        Iterator<FtpDirEntry> files = client.listFiles(dir);
        while (files.hasNext()) {
            FtpDirEntry file = files.next();
            FtpDirEntry.Type type = file.getType();

            if (type == FtpDirEntry.Type.DIR) {
                File f = new File("");
                String path = f.getAbsolutePath();
                f = new File(path + "\\" + localDir + file.getName());
                if (!f.exists() && !f.isDirectory()) {
                    boolean result = f.mkdirs();
                    System.out.println(result);
                }
                getFiles(dir + file.getName() + "/", localDir + file.getName() + "\\");
            } else if (type == FtpDirEntry.Type.FILE) {
                String fileName = file.getName();

                FileOutputStream output = new FileOutputStream(localDir + fileName);
                client.getFile(fileName, output);

                System.out.println("retrieveFile " + fileName + ";\nreply code: " + client.getLastResponseString());
                output.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MeetingClient myClient = new MeetingClientImpl();
            myClient.connectServer();
            myClient.getFiles("/", "meeting_files\\");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        }
    }
}
