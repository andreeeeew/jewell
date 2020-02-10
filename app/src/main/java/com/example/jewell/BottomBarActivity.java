package com.example.jewell;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jewell.adapter.ProductRecyclerViewAdapter;
import com.example.jewell.adapter.ScreenSlidePagerAdapter;
import com.example.jewell.adapter.StoreRecyclerViewAdapter;
import com.example.jewell.fragment.InventorizationRecyclerViewFragment;
import com.example.jewell.fragment.ProductsRecyclerViewFragment;
import com.example.jewell.fragment.StoresRecyclerViewFragment;
import com.example.jewell.general_view.BubbleNavigationLinearView;
import com.example.jewell.general_view.listener.BubbleNavigationChangeListener;
import com.example.jewell.models.Product;
import com.example.jewell.models.StockTaking;
import com.example.jewell.viewmodels.StockTakingViewModel;

import java.util.ArrayList;
import java.util.List;


public class BottomBarActivity extends AppCompatActivity {

    private ProductRecyclerViewAdapter productAdapter;
    private StoreRecyclerViewAdapter storeAdapter;
    private String TAG = "BottomBarActivity";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private List<Product> retrieveAllProducts() {
        return DataSource.Companion.createProductsDataSet();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new ProductsRecyclerViewFragment(getSupportFragmentManager(), retrieveAllProducts(), new StockTakingViewModel(new StockTaking(), ""), false));
        fragList.add(new StoresRecyclerViewFragment(getSupportFragmentManager()));
        fragList.add(new InventorizationRecyclerViewFragment(getSupportFragmentManager()));
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(fragList, getSupportFragmentManager(), getLifecycle());

        final BubbleNavigationLinearView bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
        bubbleNavigationLinearView.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));

        bubbleNavigationLinearView.setBadgeValue(0, "40");
        bubbleNavigationLinearView.setBadgeValue(1, null); //invisible badge
        bubbleNavigationLinearView.setBadgeValue(2, "7");

        final ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bubbleNavigationLinearView.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                viewPager.setCurrentItem(position, true);
            }
        });

    }

}