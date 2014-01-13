package com.org.tang.extendview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.org.tang.R;

public class OrderListAdapter extends BaseAdapter {

    private Context context;
    private List<OrderState> orderList;
    private int length;// listView的长度
    private int endSize;// listView最后行的长度（不完整时）

    public OrderListAdapter(Context context, List<OrderState> orderList) {
	this.context = context;
	this.orderList = orderList;
    }

    @Override
    public int getCount() {
	if (orderList == null || orderList.size() == 0) {
	    length = 0;
	} else {
	    int i = orderList.size() / 2;
	    endSize = orderList.size() % 2;
	    if (endSize > 0) {
		length = i + 1;
	    } else {
		length = i;
	    }

	}
	return length;
    }

    @Override
    public Object getItem(int position) {
	return orderList == null ? 0 : orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
	return orderList == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder;
	if (convertView == null) {
	    holder = new ViewHolder();
	    convertView = LayoutInflater.from(context).inflate(R.layout.item_order_state, null);
	    holder.productImage_0 = (ImageView) convertView.findViewById(R.id.product_image);
	    holder.pay_order_idname_0 = (TextView) convertView.findViewById(R.id.pay_order_idname);
	    holder.pay_pay_state_0 = (TextView) convertView.findViewById(R.id.pay_pay_state);
	    holder.pay_order_money_0 = (TextView) convertView.findViewById(R.id.pay_order_money);
	    holder.pay_order_time_0 = (TextView) convertView.findViewById(R.id.pay_order_time);

	    holder.productImage_1 = (ImageView) convertView.findViewById(R.id.product_image_1);
	    holder.pay_order_idname_0 = (TextView) convertView.findViewById(R.id.pay_order_idname_1);
	    holder.pay_pay_state_0 = (TextView) convertView.findViewById(R.id.pay_pay_state_1);
	    holder.pay_order_money_0 = (TextView) convertView.findViewById(R.id.pay_order_money_1);
	    holder.pay_order_time_0 = (TextView) convertView.findViewById(R.id.pay_order_time_1);

	    holder.layout_row_0 = (RelativeLayout) convertView.findViewById(R.id.order_row0);
	    holder.layout_row_1 = (RelativeLayout) convertView.findViewById(R.id.order_row1);

	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}
	
	if(endSize == 0){
	    setFullConverView(position, holder);
	}else {
	    switch (endSize - 1) {
	    case 0:
		
		break;

	    default:
		break;
	    }
	}
	
	return convertView;
    }

    /**
     * 满行listView初始化
     */
    private void setFullConverView(int position,ViewHolder holder){
	OrderState itemState = orderList.get(position);

//	String ss = EnumDealState.getName(itemState.getDealState());
//	holder.pay_pay_state.setText(ss);
//	holder.pay_order_time.setText(TransformDate.getLongDateTime(itemState.getOrderTime()));
//	holder.pay_order_money.setText("￥" + NumberFormator.formateFloat(orderStates.get(position).getOrderMoney()));
//
//	holder.pay_order_idname.setText(itemState.getOrderId());
//	if (orderStates.get(0).getChannel() == 0) {
//	    String imgUrl = orderStates.get(position).getProductList().get(0).getImageUrl();
//	    holder.productImage.setTag(imgUrl);
//	    imageLoader.DisplayImage(imgUrl, holder.productImage);
//	    holder.productImage.setVisibility(View.VISIBLE);
//	} else if (orderStates.get(0).getChannel() == 1) {
//	    holder.productImage.setVisibility(View.GONE);
//	}
    }
    
    
    private class ViewHolder {
	private ImageView productImage_0;
	private TextView pay_order_idname_0;
	private TextView pay_pay_state_0;
	private TextView pay_order_money_0;
	private TextView pay_order_time_0;

	private ImageView productImage_1;
	private TextView pay_order_idname_1;
	private TextView pay_pay_state_1;
	private TextView pay_order_money_1;
	private TextView pay_order_time_1;

	private RelativeLayout layout_row_0;
	private RelativeLayout layout_row_1;

    }

}
