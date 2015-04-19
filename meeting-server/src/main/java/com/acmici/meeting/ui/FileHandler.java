/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acmici.meeting.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * @author sukha
 */
public class FileHandler {
    private final Desktop desk = Desktop.getDesktop();
    ;
    private final String wrar_path = "C:/Program Files/WinRAR/WinRAR.exe ";

    public FileHandler() {

    }

    public void openFile(String path) {
        try {
            File file = new File(path);
            desk.open(file);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public boolean renameFile(String path, String old_name, String new_name) {
        if (!old_name.equals(new_name)) {
            File old_file = new File(path + "/" + old_name);
            File new_file = new File(path + "/" + new_name);
            if (!old_file.exists()) {
                return false;
            }
            if (new_file.exists()) {
                System.out.println(new_name + "已经存在！");
                return false;
            } else {
                old_file.renameTo(new_file);
                return true;
            }
        } else {
            System.out.println("新文件名和旧文件名相同...");
            return true;
        }
    }

    public void openFolder(String path) {
        String cmd = "";
        cmd = "explorer " + path;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean compressFolder(String folder_path, String target_file_path) {
        String cmd = "";
        cmd = wrar_path + "a " + target_file_path + " " + folder_path;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            if (proc.waitFor() != 0) {
                if (proc.exitValue() != 0)
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean createFolder(String path) {
        File dir = new File(path);      
        if  (dir.isDirectory()) {    
            return true;
        } else if (!dir.exists()) {
            if(dir.mkdirs())
                return true;
        }
        return false;
    }
    public boolean copyFolder(String source_path, String target_path) {

        File tar_dir = new File(target_path);
        //创建文件夹
        if (!tar_dir.isDirectory()) {
            if (!this.createFolder(target_path)) {
                System.out.println("创建文件夹失败。");
                return false;
            }
        }

        String cmd = "";
        cmd = "cmd.exe /c /e /y xcopy " + source_path + " " + target_path;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            if (proc.waitFor() != 0) {
                if (proc.exitValue() != 0)
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;    
    }
}
