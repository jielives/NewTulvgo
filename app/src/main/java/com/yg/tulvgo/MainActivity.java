package com.yg.tulvgo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.yg.tulvgo.ui.fragment.DestinationFragment;
import com.yg.tulvgo.ui.fragment.HomeFragment;
import com.yg.tulvgo.ui.fragment.MeFragment;
import com.yg.tulvgo.ui.fragment.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_fragment_container)
    FrameLayout mainFragmentContainer;
    @BindView(R.id.main_bottome_switcher_container)
    public LinearLayout mainBottomeSwitcherContainer;
    private HomeFragment homeFragment ;
    private DestinationFragment destinationFragment ;
    private OrderFragment orderFragment ;
    private MeFragment userFragment ;
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    public View.OnClickListener onClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = mainBottomeSwitcherContainer.indexOfChild(view);
                    changeUI(index);
                    changeFragment(index);
                }
            };
    public void changeFragment(int index) {
        //通过这个底部容器Item的index能获取到对应的Fragment，需要所有的fragment对号放好（集合）
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0 :
                if(homeFragment == null ) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_fragment_container,homeFragment,"homeFragmentTag");
                    hideFragment(transaction);
                    transaction.show(homeFragment);
                }else{
                    hideFragment(transaction);
                    transaction.show(homeFragment);
                }
                break;
            case 1 :
                if(destinationFragment == null ) {
                    destinationFragment = new DestinationFragment();
                    transaction.add(R.id.main_fragment_container,destinationFragment,"nearFragmentTag");
                    hideFragment(transaction);
                    transaction.show(destinationFragment);
                }else{
                    hideFragment(transaction);
                    transaction.show(destinationFragment);
                }
                break;
            case 2 :
                if(orderFragment == null ) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.main_fragment_container,orderFragment,"orderFragmentTag");
                    hideFragment(transaction);
                    transaction.show(orderFragment);
                }else{
                    hideFragment(transaction);
                    transaction.show(orderFragment);
                }
                break;
            case 3 :
                if(userFragment == null ) {
                    userFragment = new MeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("str","这是MainActivity传来的值~");
                    userFragment.setArguments(bundle);
                    transaction.add(R.id.main_fragment_container,userFragment,"userFragmentTag");
                    hideFragment(transaction);
                    transaction.show(userFragment);
                }else{
                    hideFragment(transaction);
                    transaction.show(userFragment);
                }
                break;
            default:
                break;
        }
//            transaction.commit();
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(destinationFragment != null){
            transaction.hide(destinationFragment);
        }
        if(orderFragment != null){
            transaction.hide(orderFragment);
        }
        if(userFragment != null){
            transaction.hide(userFragment);
        }
    }
    /*
        * 改变index对应的孩子的状态，包括这个孩子中所有控件的状态（不可用，enable=false）
        * 改变其他孩子的状态，也包括这些孩子中所有控件的状态
        * @param index
        * */
    public void changeUI(int index) {
        int childCount = mainBottomeSwitcherContainer.getChildCount();
        for(int i = 0; i < childCount; i++) {
//            判断i是否与index相同，相同不可用状态
            if (i == index) {
                //不可以再点击了
                mainBottomeSwitcherContainer.getChildAt(i).setEnabled(false);
                //每个Item中的控件都需要切换状态
                setEnable(mainBottomeSwitcherContainer.getChildAt(i),false);
            }else{
                mainBottomeSwitcherContainer.getChildAt(i).setEnabled(true);
                //每个item中的控件都需要切换状态
                setEnable(mainBottomeSwitcherContainer.getChildAt(i),true);
            }
        }
    }
    /*
* 将每个item中的所有控件状态一同改变
* 由于我们要处理通用的代码，那么item可能会有很多层，所以需要使用递归
* */
    private void setEnable(View item, boolean b) {
        item.setEnabled(b);//核心
        if(item instanceof ViewGroup) {//处理递归的
            int childCount = ((ViewGroup) item).getChildCount();
            for(int i = 0 ; i< childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i),b);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if(savedInstanceState != null) {//内存重启调用
//            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag("homeFragmentTag");
//            destinationFragment = (DestinationFragment) fragmentManager.findFragmentByTag("destinationFragment");
//            orderFragment = (OrderFragment) fragmentManager.findFragmentByTag("orderFragmentTag");
//            userFragment = (MeFragment) fragmentManager.findFragmentByTag("userFragmentTag");
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setLinstener();
        init();
    }
    private void init() {
        onClickListener.onClick(mainBottomeSwitcherContainer.getChildAt(0));
    }
    private void setLinstener() {
        //所有孩子不包括孙子
        int childCount = mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0 ; i < childCount; i++) {
            FrameLayout childAt = (FrameLayout) mainBottomeSwitcherContainer.getChildAt(i);
            childAt.setOnClickListener(onClickListener);
        }
    }
    //Activity内存重启调用
    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        if(homeFragment == null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag("homeFragmentTag");
//            homeFragment = (HomeFragment) fragment;
        }
        if(destinationFragment == null && fragment instanceof DestinationFragment) {
            destinationFragment = (DestinationFragment) fragmentManager.findFragmentByTag("destinationFragment");
//            destinationFragment = (DestinationFragment) fragment;
        }
        if(orderFragment == null && fragment instanceof DestinationFragment) {
            orderFragment = (OrderFragment) fragmentManager.findFragmentByTag("orderFragmentTag");
//            orderListFragment = (OrderListFragment) fragment;
        }
        if(userFragment == null && fragment instanceof MeFragment) {
            userFragment = (MeFragment) fragmentManager.findFragmentByTag("userFragmentTag");
//            userFragment = (MeFragment) fragment;
        }
    }
}
