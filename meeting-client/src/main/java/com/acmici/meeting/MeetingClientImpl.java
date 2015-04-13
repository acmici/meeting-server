package com.acmici.meeting;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by canhe on 2015/4/11.
 */
public class MeetingClientImpl implements MeetingClient {
    FTPClient client;

    public MeetingClientImpl() {
        client = new FTPClient();
    }

    @Override
    public void connectServer() throws IOException {
        client.connect("localhost", 2221);
        int rep = client.getReplyCode();
        if (!FTPReply.isPositiveCompletion(rep)) {
            client.disconnect();
            System.err.println("FTP server refused connection.");
        } else {
            System.out.println("Connected to " + "localhost" + " on " + "port 2221");
        }
    }

    @Override
    public void getFile() throws IOException {
        client.login("anonymous", "");

        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.setControlEncoding("UTF-8");
        client.enterLocalPassiveMode();

        System.out.println(client.printWorkingDirectory());
        boolean result;
        FTPFile[] files = client.listFiles();
        for (FTPFile file : files) {
            System.out.println(file);
            FileOutputStream output = new FileOutputStream("meeting_files\\" + file.getName());
//            result = client.retrieveFile(new String(file.getName().getBytes("GBK"), "iso-8859-1"), output);
            result = client.retrieveFile(file.getName(), output);
            System.out.println("retrieveFile " + file.getName() + " result: " + result + ";\nreply code: " + client.getReplyString());
            output.close();
        }

        FileInputStream input = new FileInputStream("meeting_files\\新建文本文档.txt");
        client.storeFile(new String("新建文本.txt".getBytes("UTF-8")), input);
        System.out.println(new String(client.getReplyString().getBytes("GBK"), "iso-8859-1"));
    }

    public static void main(String[] args) {
        MeetingClient myClient = new MeetingClientImpl();
        try {
            myClient.connectServer();
            myClient.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
