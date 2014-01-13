package com.org.tang.extendview;

import com.org.tang.R;
import com.org.tang.R.color;
import com.org.tang.R.id;
import com.org.tang.R.layout;
import com.org.tang.R.string;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyListView extends ListView implements OnScrollListener {

	// ������ظ��ö��״̬
	private enum DListViewLoadingMore {
		LV_LOADING, // ����״̬
		LV_LOADED, // �������
		LV_OVER; // ����״̬
	}

	private View mFootView;
	private TextView footerMoreView;
	private ProgressBar footerProgressBar;
	private OnBottomRefreshLoadingListener onBottomRefreshLoadingListener;
	
	private boolean flag;
	
	public MyListView(Context context) {
		super(context);
		init(context);
		initLoadMoreView(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		initLoadMoreView(context);
	}

	private void init(Context context) {
		setCacheColorHint(context.getResources().getColor(R.color.transparent));
		setOnScrollListener(this);
		setHorizontalFadingEdgeEnabled(false);

	}

	/***
	 * ��ʼ���ײ����ظ��ؼ�
	 */
	private void initLoadMoreView(Context context) {
		mFootView = LayoutInflater.from(context).inflate(
R.layout.listview_footer, null);

		footerMoreView = (TextView) mFootView.findViewById(R.id.more);
		footerProgressBar = (ProgressBar) mFootView
				.findViewById(R.id.footer_progressBar);
		
		mFootView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (onBottomRefreshLoadingListener != null  && !flag) {
					updateLoadMoreViewState(DListViewLoadingMore.LV_LOADING);
					onBottomRefreshLoadingListener.onLoadMore();
				}
			}
		});

		addFooterView(mFootView);

	}

	public void setOnBottomRefreshListner(
			OnBottomRefreshLoadingListener reListener) {
		this.onBottomRefreshLoadingListener = reListener;
	}

	// ����Footview��ͼ
	private void updateLoadMoreViewState(DListViewLoadingMore state) {
		switch (state) {
		// ������״̬
		case LV_LOADING:
			footerProgressBar.setVisibility(View.VISIBLE);
			footerMoreView.setVisibility(View.VISIBLE);
			footerMoreView.setText(R.string.load_more_information);
			flag=true;
			break;
		// �������״̬
		case LV_OVER:
			footerProgressBar.setVisibility(View.GONE);
			footerMoreView.setVisibility(View.VISIBLE);
			footerMoreView.setText(R.string.load_over);
			break;
			//��һ�μ��ؽ���
		case LV_LOADED:
			footerProgressBar.setVisibility(View.GONE);
			footerMoreView.setVisibility(View.VISIBLE);
			footerMoreView.setText(R.string.loaded);
			flag=false;
			break;
		default:
			break;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			int lastPostion = this.getLastVisiblePosition(); // ��0��ʼ��.
			int size = this.getCount();
			if ((size - 1) == lastPostion) {
				if (onBottomRefreshLoadingListener != null  && !flag) {
					updateLoadMoreViewState(DListViewLoadingMore.LV_LOADING);
					onBottomRefreshLoadingListener.onLoadMore();
					
				}
			}

		}
	}
	
   

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (totalItemCount <= visibleItemCount) {
			if (mFootView != null) {
			    mFootView.setVisibility(View.GONE);
			}
		} else {
			if (mFootView != null) {
				mFootView.setVisibility(View.VISIBLE);
			}
		}

	}

	public void loadComplete() {
		updateLoadMoreViewState(DListViewLoadingMore.LV_OVER);
	}
	
	public void loadedComplete() {
		updateLoadMoreViewState(DListViewLoadingMore.LV_LOADED);
	}
	
	/**
	 * ����ʵfootView
	 */
	public void setFootViewGone(){
	    mFootView.setVisibility(View.GONE);
	}
	/**
	 * 
	 * @author tang_zhengzong
	 * listView���ظ�����ʱ��
	 */
	public interface OnBottomRefreshLoadingListener {
		void onLoadMore();
	}
	

}

