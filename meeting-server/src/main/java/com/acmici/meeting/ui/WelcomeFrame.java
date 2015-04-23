/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acmici.meeting.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author sukha
 */
public class WelcomeFrame extends JFrame {
    private JLabel welcome_label;
    private JLabel logo;
    private JButton create_button;
    private JButton read_button;

    public WelcomeFrame() {
        super();
        this.setSize(500, 300);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        this.add(this.getWelcomeLabel());
        this.add(this.getLogo());
        this.add(this.getCreateButton());
        this.add(this.getReadButton());

        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
        this.setLocation(w, h);
        this.setTitle("国防科技大学无纸化会议系统-服务器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //修改左上角图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\thunder.jpg"));
    }

    private JLabel getWelcomeLabel() {
        if (welcome_label == null) {
            welcome_label = new JLabel();
            welcome_label.setBounds(50, 90, 200, 20);
            welcome_label.setFont(new Font("黑体", Font.PLAIN, 15));
            welcome_label.setText("欢迎使用无纸化会议系统！");
        }
        return welcome_label;
    }

    private JLabel getLogo() {
        if (logo == null) {
            logo = new JLabel();
            logo.setBounds(270, 30, 150, 150);
            logo.setIcon(new ImageIcon("f:\\会议系统\\logo.jpg"));
        }
        return logo;
    }

    private JButton getCreateButton() {
        if (create_button == null) {
            create_button = new JButton();
            create_button.setBounds(100, 200, 100, 30);
            create_button.setFont(new Font("宋体", Font.PLAIN, 15));
            create_button.setText("创建会议");
            create_button.setToolTipText("单击创建会议");
            create_button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    createButtonActionPerformed(evt);
                }
            });
        }
        return create_button;
    }

    private JButton getReadButton() {
        if (read_button == null) {
            read_button = new JButton();
            read_button.setBounds(300, 200, 100, 30);
            read_button.setFont(new Font("宋体", Font.PLAIN, 15));
            read_button.setText("查询会议");
            read_button.setToolTipText("单击查询会议历史");
            read_button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    readButtonActionPerformed(evt);
                }
            });
        }
        return read_button;
    }

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        WizardDialog wizard_dialog = new WizardDialog(this, true);
        //this.setVisible(false);
        wizard_dialog.setVisible(true);
    }

    private void readButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        HistroyFrame histroy_frame = new HistroyFrame();
        histroy_frame.setVisible(true);
        //this.setVisible(false);
    }
}
