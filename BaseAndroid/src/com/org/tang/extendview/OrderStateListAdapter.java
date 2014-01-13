package com.org.tang.extendview;

import java.util.List;

import com.org.tang.R;
import com.org.tang.R.id;
import com.org.tang.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderStateListAdapter extends BaseAdapter {
    private Context context;
    private List<OrderState> orderStates;
    private boolean isOnline;

    public boolean isOnline() {
	return isOnline;
    }

    public void setOnline(boolean isOnline) {
	this.isOnline = isOnline;
    }

    // private AsyncImageLoader imageLoader;

    /**
     * 
     * @param context
     * @param orderStates
     * 
     *            线上订单isOnline为true 线下为false
     * 
     */
    public OrderStateListAdapter(Context context, List<OrderState> orderStates) {
	this.context = context;
	this.orderStates = orderStates;
    }

    @Override
    public int getCount() {
	return orderStates.size();
    }

    @Override
    public Object getItem(int position) {
	return orderStates.get(position);
    }

    @Override
    public long getItemId(int position) {
	return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

	ProductHolder holder;
	if (convertView == null) {
	    holder = new ProductHolder();
	    convertView = LayoutInflater.from(context).inflate(R.layout.item_order_state, null);
	    holder.productImage = (ImageView) convertView.findViewById(R.id.product_image);
	    holder.pay_order_idname = (TextView) convertView.findViewById(R.id.pay_order_idname);
	    holder.pay_pay_state = (TextView) convertView.findViewById(R.id.pay_pay_state);
	    holder.pay_order_money = (TextView) convertView.findViewById(R.id.pay_order_money);
	    holder.pay_order_time = (TextView) convertView.findViewById(R.id.pay_order_time);

	    convertView.setTag(holder);
	} else {
	    holder = (ProductHolder) convertView.getTag();
	}

	OrderState itemState = orderStates.get(position);

	holder.pay_pay_state.setText("sss");
	holder.pay_order_time.setText("1990");
	holder.pay_order_money.setText("$111");

	holder.pay_order_idname.setText(itemState.getOrderId());
	if (orderStates.get(0).getChannel() == 0) {
	    holder.productImage.setVisibility(View.VISIBLE);
	} else if (orderStates.get(0).getChannel() == 1) {
	    holder.productImage.setVisibility(View.GONE);
	}

	return convertView;
    }

    private class ProductHolder {

	private ImageView productImage;
	private TextView pay_order_idname;
	private TextView pay_pay_state;
	// private Button go_to_pay;
	private TextView pay_order_money;
	private TextView pay_order_time;

    }

}
