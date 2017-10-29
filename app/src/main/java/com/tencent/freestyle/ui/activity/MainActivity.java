package com.tencent.freestyle.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import com.tencent.freestyle.R;
import com.tencent.freestyle.ui.adapter.MainTabAdapter;
import com.tencent.freestyle.ui.base.BaseActivity;
import com.tencent.freestyle.ui.base.BaseFragment;
import com.tencent.freestyle.ui.base.BasePresenter;
import com.tencent.freestyle.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @BindView(R.id.vp_content)
    ViewPager mViewPager;

    private List<BaseFragment> mFragments;
    private MainTabAdapter mTabAdapter;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
    }

    @Override
    public void initListener() {
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_dashboard:
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                }
                return false;
            }
        });
        mTabAdapter = new MainTabAdapter(mFragments,getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEventBus(MainActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterEventBus(MainActivity.this);
    }
}
