package com.org.tang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TestActivity extends Activity {
    private EditText editText;
    private String past = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);

	setContentView(R.layout.layout_test);
	editText = (EditText) findViewById(R.id.edit);
	editText.addTextChangedListener(new TextWatcher() {

	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

		String input = editText.getText().toString();
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("###,###");
		try {
		    Number number = myformat.parse(input);
		    String now = myformat.format(Integer.valueOf(number + ""));
		    if (!past.equals(input)) {
			past = input;
			editText.setText(now);
		    }
		} catch (ParseException e) {
		    e.printStackTrace();
		}

	    }
	});

    }

    /**
     * 格式化金额
     * 
     * @param s
     * @param len
     * @return
     */
    public static String formatMoney(String s, int len) {
	if (s == null || s.length() < 1) {
	    return "";
	}
	NumberFormat formater = null;
	float num = Float.parseFloat(s);
	if (len == 0) {
	    formater = new DecimalFormat("###,###");

	} else {
	    StringBuffer buff = new StringBuffer();
	    buff.append("###,###.");
	    for (int i = 0; i < len; i++) {
		buff.append("#");
	    }
	    formater = new DecimalFormat(buff.toString());
	}
	String result = formater.format(num);
//	if (result.indexOf(".") == -1) {
//	    result = "￥" + result + ".00";
//	} else {
//	    result = "￥" + result;
//	}
	return result;
    }
    
    public static String restoreMoney(String s){
	return null;
    }
}
