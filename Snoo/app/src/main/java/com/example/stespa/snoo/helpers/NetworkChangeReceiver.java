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
        String message = "";
        boolean isConnected = NetworkUtil.isConnected(context);
        message = !isConnected ? "Network is unavailable." : "Network is available";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}