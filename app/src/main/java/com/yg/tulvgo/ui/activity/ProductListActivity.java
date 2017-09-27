package com.yg.tulvgo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yg.common.base.BaseActivity;
import com.yg.common.customview.MultiShapeView;
import com.yg.common.dropdownmenu.DropDownMenu;
import com.yg.common.lrecyclerview.SRecyclerView;
import com.yg.common.statusbar.StatusBarCompat;
import com.yg.tulvgo.R;
import com.yg.tulvgo.model.TestModel;
import com.yg.tulvgo.presenter.TestPresenter;
import com.yg.tulvgo.ui.adapter.MenuListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**产品列表*/
public class ProductListActivity extends BaseActivity<TestPresenter,TestModel> {
    SRecyclerView mRproductList;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String Headers[] = {"目的地","景区门票","智能排序","筛选"};
    private List<View> popupViews = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    ListView listView1;
    ListView listView2;
    ListView listView3;
    ListView listView4;
    MenuListAdapter menuAdapter1;
    MenuListAdapter menuAdapter2;
    MenuListAdapter menuAdapter3;
    MenuListAdapter menuAdapter4;
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String sexs2[] = {"不限", "男", "女"};
    @Override
    protected int setLayoutResouceId() {
        return R.layout.activity_product_list;
    }
    @Override
    protected void initData() {
        super.initData();
    }
    @Override
    protected void initView() {
        super.initView();
        StatusBarCompat.setStatusBarColor(this, Color.BLACK);
        listView1 = new ListView(ProductListActivity.this);
        listView2 = new ListView(ProductListActivity.this);
        listView3 = new ListView(ProductListActivity.this);
        listView4 = new ListView(ProductListActivity.this);

        listView1.setDividerHeight(0);
        listView2.setDividerHeight(0);
        listView3.setDividerHeight(0);
        listView4.setDividerHeight(0);

        menuAdapter1 = new MenuListAdapter(ProductListActivity.this,Arrays.asList(citys));
        menuAdapter2 = new MenuListAdapter(ProductListActivity.this,Arrays.asList(ages));
        menuAdapter3 = new MenuListAdapter(ProductListActivity.this,Arrays.asList(sexs));
        menuAdapter4 = new MenuListAdapter(ProductListActivity.this,Arrays.asList(sexs2));

        listView1.setAdapter(menuAdapter1);
        listView2.setAdapter(menuAdapter2);
        listView3.setAdapter(menuAdapter3);
        listView4.setAdapter(menuAdapter4);

        popupViews.add(listView1);
        popupViews.add(listView2);
        popupViews.add(listView3);
        popupViews.add(listView4);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDropDownMenu.setTabText(sexs2[position]);
                mDropDownMenu.closeMenu();
            }
        });
        View contentView = getLayoutInflater().inflate(R.layout.product_list_content_layout,null);
        mRproductList = (SRecyclerView) contentView.findViewById(R.id.srv_productlist);
        mDropDownMenu.setDropDownMenu(Arrays.asList(Headers),popupViews,contentView);

        mRproductList.setLoadListener(new SRecyclerView.LoadListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        mRproductList.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void loading() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       if(list.size() != 30){
                           loadData();
                           mRproductList.loadingComplete();
                       }else {
                           mRproductList.loadNoMoreData();
                       }
                    }
                },2000);
            }
        });

      //item点击事件
        mRproductList.setItemClickListener(new SRecyclerView.ItemClickListener() {
            @Override
            public void click(View v, int position) {
                Intent i = new Intent(ProductListActivity.this,ScenicSpotDetailsPageActivity.class);
                startActivity(i);
            }
        });

//        mRproductList.setDivider(Color.LTGRAY,3,30,0);
        mRproductList.setAdapter(new InitAdapter(list,this));
        mRproductList.startRefresh(true);
    }

    private void loadData() {
        for(int i=15; i<30;i++){
            list.add("数据"+i);
        }
        mRproductList.getAdapter().notifyDataSetChanged();
    }

    private void refreshData() {
        list.clear();
        for(int i = 0 ;i < 15 ;i++){
            list.add("数据"+i);
        }
        mRproductList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void initPresenter() {

    }


    /**
     * 原生的适配器
     */
    private static class InitAdapter extends RecyclerView.Adapter<InitAdapter.Holder> {
        private LayoutInflater inflater;
        private List<String> list;

        InitAdapter(List<String> list, Context context) {
            this.list = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.item_productlist, parent, false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(list.get(position));
            holder.iv.setImageResource(R.drawable.a);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class Holder extends RecyclerView.ViewHolder {
            private TextView textView;
            private ImageView iv;
            Holder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_item_test);
                iv = (ImageView) itemView.findViewById(R.id.iv_round);
            }
        }
    }
}
