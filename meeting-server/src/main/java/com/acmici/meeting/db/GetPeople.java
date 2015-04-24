/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acmici.meeting.db;

import com.acmici.meeting.db.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar; 


/**
 *
 * @author nie
 */
public class GetPeople {
    
    
    public static void main(String args[]){
        Server server = new Server();
        String start = "2014-02-02";
        String end = "2015-02-02";
        List list =  server.GetMeetingByInformation("五");
                for(int i=0;i<list.size();i++){
             MeetingVo meeting =  (MeetingVo) list.get(i);
             Integer a = meeting.getId();
            System.out.println(a);
        }
	}
}
