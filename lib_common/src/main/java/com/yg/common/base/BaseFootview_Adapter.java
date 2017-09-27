package com.yg.common.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.yg.common.R;

import java.util.ArrayList;

/**
 * =====================
 * Author: shenjie
 * Date: 13-11-1
 * Time: 下午14:17
 * describe:ticketFragment中的ListView适配器的基类
 * =====================
 */

public abstract class BaseFootview_Adapter<E> extends BaseAdapter {

    public static final int DEFAULT_SHOW_COUNT = 3;

    protected Context mContext;
    protected ListView mListView;
    protected LayoutInflater inflater;
    protected LinearLayout footerHeadView;
    protected Button btn_loadmore;
    protected ArrayList<E> mShowObjects = new ArrayList<E>();
    protected ArrayList<E> mAllObjects = null;
    protected boolean shrink = true;//是否折叠

    @SuppressWarnings("unused")
    private BaseFootview_Adapter() {
    }

    @SuppressLint("InflateParams")
    public BaseFootview_Adapter(Context mContext, ListView mListView) {
        this.mContext = mContext;
        this.mListView = mListView;
        inflater = LayoutInflater.from(mContext);
        footerHeadView= (LinearLayout) inflater.inflate(R.layout.lv_footer_more, null);
        btn_loadmore = (Button) footerHeadView.findViewById(R.id.btn_loadmore);

        /*
        * 给查看更多(收起)按钮设置点击事件
        *
        * */
        btn_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeShow();//改变显示的方式
            }
        });
        mListView.addFooterView(footerHeadView, null, false);
    }
    /*
    * 数据源中的数据
    *     如果小于等于默认显示的item条数，就将按钮隐藏，将数据源集合中全部数据添加到显示的集合里
    *     否则就将按钮显示，将默认显示条目的数据添加到显示集合
    *     更新数据
    *     动态设置ListView的高度
    * */
    public void setAdapterData(ArrayList<E> mAllObjects ) {
        this.mAllObjects = mAllObjects;
        mShowObjects.clear();
        if( mAllObjects != null ) {
            if( mAllObjects.size() <= DEFAULT_SHOW_COUNT ) {
                footerHeadView.setVisibility(View.GONE);
                mShowObjects.addAll(mAllObjects);
            } else {
                footerHeadView.setVisibility(View.VISIBLE);
                for (int i = 0; i < DEFAULT_SHOW_COUNT; i++) {
                    mShowObjects.add(mAllObjects.get(i));
                }
            }
        }
        notifyDataSetChanged();
        setListViewHeightBasedOnChildren(mListView);
    }

    /*
    * 返回默认显示的item数目条数
    * */
    @Override
    public int getCount() {
        int showCount = 0;
        if( mShowObjects != null ) {
            showCount = mShowObjects.size();
        }
        return showCount;
    }

    @Override
    public E getItem(int position) {
        E object = null;
        if( mShowObjects != null ) {
            object = mShowObjects.get(position);
        }
        return object;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //改变显示的方式
    private void changeShow() {
        if( footerHeadView.getVisibility() == View.GONE ) {
            footerHeadView.setVisibility(View.VISIBLE);
        }
        mShowObjects.clear();//清空显示数据源集合
        if( shrink ) {//判断是否折叠，默认是true
            shrink = false;
            mShowObjects.addAll(mAllObjects);//将数据源数据全部添加到显示数据的集合
            btn_loadmore.setText("收起");//并按钮的文本设为收起
        } else {
            shrink = true;
            for (int i = 0; i < DEFAULT_SHOW_COUNT; i++) {
                mShowObjects.add(mAllObjects.get(i));
            }
            btn_loadmore.setText("查看更多");
        }
        notifyDataSetChanged();
        setListViewHeightBasedOnChildren(mListView);
    }

    /**
     * 当ListView外层有ScrollView时，需要动态设置ListView高度
     * @param listView
     */
    protected void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
