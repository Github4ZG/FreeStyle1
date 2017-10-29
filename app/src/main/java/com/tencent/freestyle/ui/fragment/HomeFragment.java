package com.tencent.freestyle.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.tencent.freestyle.R;
import com.tencent.freestyle.ui.base.BaseFragment;
import com.tencent.freestyle.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Greyzhou on 2017/10/13.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.vp_content)
    ViewPager mViewPager;

    @BindView(R.id.tab_channel)
    TabLayout mTabs;

    private List<String> mChannels = new ArrayList<>();
    private List<NewsListFragment> mChannelFragments = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void initData() {

    }
}
