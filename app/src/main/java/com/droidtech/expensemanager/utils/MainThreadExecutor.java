package com.droidtech.expensemanager.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    @Override
    public void execute(Runnable command) {
        mainThreadHandler.post(command);
    }
}
