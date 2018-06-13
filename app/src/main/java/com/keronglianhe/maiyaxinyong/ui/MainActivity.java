package com.keronglianhe.maiyaxinyong.ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.keronglianhe.maiyaxinyong.R;
import com.keronglianhe.maiyaxinyong.ui.fragment.CreditEnquiryFragment;
import com.keronglianhe.maiyaxinyong.ui.fragment.HistoryReportFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private AlphaTabsIndicator alphaTabsIndicator;
    private List<TabItem> mTableItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //全透明状态栏设置
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setTransparentStatusBar(this);
        initTabData();
        initTabHost();

    }

    /**
     * 设置沉浸式状态栏
     * @param activity
     */
    public static void setTransparentStatusBar(Activity activity) {
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            //4.4到5.0
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    //初始化Tab数据
    private void initTabData() {
        mTableItemList = new ArrayList<>();
        //添加tab
        mTableItemList.add(new TabItem(R.mipmap.creditenquiry1, R.mipmap.creditenquiry, R.string.main_home_text, CreditEnquiryFragment.class));
        mTableItemList.add(new TabItem(R.mipmap.historyreport1, R.mipmap.historyreport, R.string.main_attention_text, CreditEnquiryFragment.class));
        mTableItemList.add(new TabItem(R.mipmap.personal1, R.mipmap.personal, R.string.main_mine_text, CreditEnquiryFragment.class));

    }

    //初始化选项卡视图
    private void initTabHost() {
        //实例化FragmentTabHost对象
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mTableItemList.size(); i++) {
            TabItem tabItem = mTableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView());
            fragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.main_bottom_bg));

            //默认选中第一个tab
            if (i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i < mTableItemList.size(); i++) {
                    TabItem tabitem = mTableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        tabitem.setChecked(true);
                    } else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });
    }


    class TabItem {
        //正常情况下显示的图片
        private int imageNormal;
        //选中情况下显示的图片
        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;

        //tab对应的fragment
        public Class<? extends Fragment> fragmentClass;

        public View view;
        public ImageView imageView;
        public TextView textView;

        public TabItem(int imageNormal, int imagePress, int title, Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return title;
        }

        public String getTitleString() {
            if (title == 0) {
                return "";
            }
            if (TextUtils.isEmpty(titleString)) {
                titleString = getString(title);
            }
            return titleString;
        }

        public View getView() {
            if (this.view == null) {
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
                this.imageView = (ImageView) this.view.findViewById(R.id.tab_iv_image);
                this.textView = (TextView) this.view.findViewById(R.id.tab_tv_text);
                if (this.title == 0) {
                    this.textView.setVisibility(View.GONE);
                } else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }

        //切换tab的方法
        public void setChecked(boolean isChecked) {
            if (imageView != null) {
                if (isChecked) {
                    imageView.setImageResource(imagePress);
                } else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if (textView != null && title != 0) {
                if (isChecked) {
                    textView.setTextColor(getResources().getColor(R.color.main_botton_text_select));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.main_bottom_text_normal));
                }
            }
        }
    }
}
