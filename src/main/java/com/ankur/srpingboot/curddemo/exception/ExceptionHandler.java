package com.ankur.srpingboot.curddemo.exception;


import io.netty.channel.Channel;
import reactor.netty.Connection;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
public class ExceptionHandler extends NewTestClass {

    @Override
    public void display() throws IOException {
       System.out.print("I am main");
    }
}
