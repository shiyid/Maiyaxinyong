package com.keronglianhe.maiyaxinyong.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.keronglianhe.maiyaxinyong.R;
import com.keronglianhe.maiyaxinyong.utils.NoMultiClickListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditEnquiryFragment extends Fragment {

    private SimpleMarqueeView<Spanned> marqueeView;

    private MZBannerView mMZBanner;
    public static final int[] BANNER = new int[]{R.mipmap.banner2, R.mipmap.banner1, R.mipmap.banner3, R.mipmap.banner4};


    private TabLayout tab_essence;
    private ViewPager vp_essence;

    TextView title;


    public CreditEnquiryFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_enquiry, container, false);

//        marqueeView = view.findViewById(R.id.marqueeView);
//        initMarqueeView();
        title= view.findViewById(R.id.title_text);
        title.setText("登录");
        initBanner(view);
        initConentView(view);
        setupCollapsingToolbar(view);
        return view;
    }


    private void setupCollapsingToolbar(View view) {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(
                R.id.collapse_toolbar);

        collapsingToolbar.setTitleEnabled(false);
    }


//    /**
//     * 插入跑马灯数据
//     */
//    private void initMarqueeView() {
//        SimpleMF<Spanned> marqueeFactory = new SimpleMF<>(getContext());
//        List<Spanned> datas = new ArrayList<>();
//        datas.add(Html.fromHtml("账户<font color=\"#ff0000\">156****1234</font>刚刚获得了<font color=\"#ff0000\">5.76</font>元现金奖励。"));
//        datas.add(Html.fromHtml("账户<font color=\"#ff0000\">156****4214</font>刚刚获得了<font color=\"#ff0000\">6.44</font>元现金奖励。"));
//        datas.add(Html.fromHtml("账户<font color=\"#ff0000\">158****1251</font>刚刚获得了<font color=\"#ff0000\">2.54</font>元现金奖励。"));
//        datas.add(Html.fromHtml("账户<font color=\"#ff0000\">159****1245</font>刚刚获得了<font color=\"#ff0000\">8.45</font>元现金奖励。"));
//        datas.add(Html.fromHtml("账户<font color=\"#ff0000\">159****1754</font>刚刚获得了<font color=\"#ff0000\">7.68</font>元现金奖励。"));
//        marqueeFactory.setData(datas);
//        marqueeView.setMarqueeFactory(marqueeFactory);
//        marqueeView.startFlipping();
//    }

    /**
     * 轮播图
     *
     * @param view
     */
    private void initBanner(View view) {
        mMZBanner = view.findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener(new NoMultiClickListener() {
            @Override
            public void onNoMultiClick(View view, int position) {
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                }
            }
        });
        mMZBanner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mMZBanner.setDuration(1000);
        List<Integer> bannerList = new ArrayList<>();
        for (int i = 0; i < BANNER.length; i++) {
            bannerList.add(BANNER[i]);
        }
        mMZBanner.setIndicatorVisible(true);
        mMZBanner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }

    /**
     * Tab标签
     *
     * @param view
     */
    public void initConentView(View view) {
        this.tab_essence = (TabLayout) view.findViewById(R.id.tab_essence);
        this.vp_essence = (ViewPager) view.findViewById(R.id.vp_essence);
        setupViewPager(vp_essence);
        tab_essence.setupWithViewPager(vp_essence);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new WindControlFragment(), "风控验证");
        adapter.addFrag(new RiskEnquiryFragment(), "风险查询");

        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
