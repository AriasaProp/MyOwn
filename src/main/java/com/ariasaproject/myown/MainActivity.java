package com.ariasaproject.myown;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.ariasaproject.myown.fragments.DashboardFragment;
import com.ariasaproject.myown.fragments.DrawFragment;
import com.ariasaproject.myown.fragments.ShapeFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        // tabs and viewpager
        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager2 viewPager = findViewById(R.id.viewPager);

        pagerAdapter =
                new FragmentStateAdapter(this) {
                    @NonNull
                    @Override
                    public Fragment createFragment(int position) {
                        switch (position) {
                            case 0:
                                return new ShapeFragment();
                            default:
                            case 1:
                                return new DashboardFragment();
                            case 2:
                                return new DrawFragment();
                        }
                    }

                    @Override
                    public int getItemCount() {
                        return 3;
                    }
                };
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    private int previousScrollState = SCROLL_STATE_IDLE;
                    private int scrollState = SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrollStateChanged(final int state) {
                        previousScrollState = scrollState;
                        scrollState = state;
                        // tabLayout.updateViewPagerScrollState(scrollState);
                    }

                    @Override
                    public void onPageScrolled(
                            int p, float positionOffset, int positionOffsetPixels) {
                        boolean updateSelectedTabView =
                                scrollState != SCROLL_STATE_SETTLING
                                        || previousScrollState == SCROLL_STATE_DRAGGING;
                        boolean updateIndicator =
                                !(scrollState == SCROLL_STATE_SETTLING
                                        && previousScrollState == SCROLL_STATE_IDLE);
                        tabLayout.setScrollPosition(
                                p, positionOffset, updateSelectedTabView, updateIndicator);
                    }

                    @Override
                    public void onPageSelected(final int position) {
                        if (tabLayout.getSelectedTabPosition() != position) {
                            boolean updateIndicator =
                                    scrollState == SCROLL_STATE_IDLE
                                            || (scrollState == SCROLL_STATE_SETTLING
                                                    && previousScrollState == SCROLL_STATE_IDLE);
                            tabLayout.selectTab(tabLayout.getTabAt(position), updateIndicator);
                        }
                    }
                });
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(@NonNull TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition(), true);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                });
        viewPager.setCurrentItem(1, false);
        // check feature
        checkBatteryOptimizations();
    }

    private static final int REQUEST_BATTERY_OPTIMIZATIONS = 1001;

    private void checkBatteryOptimizations() {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null
                && !powerManager.isIgnoringBatteryOptimizations(getPackageName())) {
            // Jika izin tidak diizinkan, tampilkan dialog untuk meminta izin
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_BATTERY_OPTIMIZATIONS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            default:
                break;
            case REQUEST_BATTERY_OPTIMIZATIONS:
                PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                if (powerManager != null
                        && powerManager.isIgnoringBatteryOptimizations(getPackageName())) {
                    // Izin diberikan, lanjutkan dengan operasi normal
                } else {
                    // Izin ditolak, berikan pengguna instruksi lebih lanjut atau tindakan yang
                    // sesuai
                }
                break;
        }
    }
}
