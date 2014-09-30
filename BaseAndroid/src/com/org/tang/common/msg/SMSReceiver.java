package com.org.tang.common.msg;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	private String verifyCode = "";
	public static final String TAG = "SMSReceiver";
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
			SmsMessage[] messages = getMessagesFromIntent(intent);
			for (SmsMessage message : messages) {
				
				String msgall = "getOriginatingAddress: "+message.getOriginatingAddress() + " \n " + "getDisplayOriginatingAddress: "+message.getDisplayOriginatingAddress()
						+ " \n " + "getDisplayMessageBody: "+message.getDisplayMessageBody() + " \n " +"getTimestampMillis: "+ message.getTimestampMillis()+"\n";
				
				Log.i(TAG, msgall);
				String smsContent ="getDisplayMessageBody: "+ message.getDisplayMessageBody();
				Log.i(TAG, smsContent);
				Toast.makeText(context, smsContent, Toast.LENGTH_LONG).show();
				writeFile(msgall + smsContent);// 将短信内容写入SD卡
			}
		}
	}
	
	

	private final SmsMessage[] getMessagesFromIntent(Intent intent) {
		Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
		byte[][] pduObjs = new byte[messages.length][];
		for (int i = 0; i < messages.length; i++) {
			pduObjs[i] = (byte[]) messages[i];
		}
		byte[][] pdus = new byte[pduObjs.length][];
		int pduCount = pdus.length;
		SmsMessage[] msgs = new SmsMessage[pduCount];
		for (int i = 0; i < pduCount; i++) {
			pdus[i] = pduObjs[i];
			msgs[i] = SmsMessage.createFromPdu(pdus[i]);
		}
		return msgs;
	}

	// 将短信内容写到SD卡上的文件里，便于将文件pull到PC，这样可方便其它如WWW/WAP平台的自动化
	@SuppressLint("SdCardPath")
	private void writeFile(String str) {
		String filePath = File.separator+"sdcard"+File.separator+"verifyCode.txt";
		byte[] bytes = str.getBytes();
		try {
			File file = new File(filePath);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(String str) throws IOException {
		File file = new File(str);
		FileInputStream fis = new FileInputStream(file);
		StringBuffer sb = new StringBuffer();

		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedReader read = new BufferedReader(new InputStreamReader(bis));
		int c = 0;
		while ((c = read.read()) != -1) {
			sb.append((char) c);
		}
		read.close();
		bis.close();
		fis.close();
		Log.i(TAG, sb.toString());
		String verify = sb.toString();
		return verify;
	}

}
