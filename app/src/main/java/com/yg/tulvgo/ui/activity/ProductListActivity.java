package com.yg.tulvgo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yg.common.base.BaseActivity;
import com.yg.common.dropdownmenu.DropDownMenu;
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
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String Headers[] = {"目的地","景区门票","智能排序","筛选"};
    private List<View> popupViews = new ArrayList<>();
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

      mDropDownMenu.setDropDownMenu(Arrays.asList(Headers),popupViews,contentView);
    }

    @Override
    public void initPresenter() {

    }
}
