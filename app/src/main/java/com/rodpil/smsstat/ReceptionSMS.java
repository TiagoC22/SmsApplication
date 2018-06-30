package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ReceptionSMS extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] objects = (Object[]) bundle.get("objects");
                if (objects.length == 0) {
                    return;
                }
                SmsMessage[] messages = new SmsMessage[objects.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < objects.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();


                Log.i("SENDER", sender);
                Log.i("MSG", message);

                this.doProcess(context, sender, message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doProcess(Context context, String sender, String message) {
        incrementCounter(context, sender, message);
    }

    private void receiver(Context context, String sender, String message, JSONObject c) {
        try {
            JSONArray rules = c.getJSONArray("rules");
            if (rules.length() != 0) {
                for (int i = 0; i < rules.length(); i++) {
                    JSONObject rule = rules.getJSONObject(i);
                    String msg = rule.getString("UnMessage");
                    if (rule.has("contains")) {
                        Log.i("Message 0","Oui");
                        String contains = rule.getString("contains");
                        if (message.contains(contains)) {
                            sendSMS(context,sender,msg);
                        } } else {
                        sendSMS(context,sender,msg);
                        Log.i("Message 1","Envoyée");
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendSMS(Context context, String phoneNumber, String message) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(context, "Envoyée!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Pas envoyée", Toast.LENGTH_LONG).show();

        }
    }


    private void incrementCounter(Context context, String sender, String message) {
        InputStream is = null;
        try {
            is = context.openFileInput("contacts.json");

            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, "UTF-8");
            JSONObject json = new JSONObject(jsonString);

            // Getting JSON Array node
            JSONArray contacts = json.getJSONArray("Contacts");

            // looping through All Contacts
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);

                String phone = c.getString("Téléphone");
                if (phone.startsWith("0"))
                    phone = phone.replaceFirst("0", "+33");

                if (phone.equals(sender)) {
                    String count = c.getString("Message");
                    Integer res = Integer.parseInt(count) + 1;
                    c.put("Message", res);
                    Log.i("C", c.toString());
                    receiver(context, sender, message, c);

                }
                Toast.makeText(context, "nope", Toast.LENGTH_LONG).show();

            }

            json.put("contacts", contacts);
            Fichiers.writeFile(context, "contacts.json", json);
            Fichiers.readFile(context, "contacts.json");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}