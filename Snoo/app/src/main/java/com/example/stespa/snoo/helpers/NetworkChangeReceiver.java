package com.example.stespa.snoo.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by stespa on 2017-02-26.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        int status = NetworkUtil.getConnectivityStatusString(context);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if(status==NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
                Toast.makeText(context, "Network is unavailable.", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, "Network is available.", Toast.LENGTH_LONG).show();
            }
        }
    }
}