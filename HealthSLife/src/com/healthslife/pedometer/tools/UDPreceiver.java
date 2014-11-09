package com.healthslife.pedometer.tools;

import android.content.Context;
import android.util.Log;

/**
 * Created by wei on 14-9-19.
 */
public class UDPreceiver extends udpmessenger implements Runnable{

    private Runnable run = this;
    @Override
    public void run() {
    }

    @Override
    public Runnable getIncomingMessageAnalyseRunnable() {

        return run;
    }

    public UDPreceiver(Context context, String tag){
    	super(context, tag , 59995);
        //super(context,tag,59995);
    }
}
