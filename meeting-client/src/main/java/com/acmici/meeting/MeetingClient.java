package com.acmici.meeting;

import java.io.IOException;

/**
 * Created by canhe on 2015/4/11.
 */
public interface MeetingClient {
    public void connectServer() throws IOException;

    public void getFile() throws IOException;
}
