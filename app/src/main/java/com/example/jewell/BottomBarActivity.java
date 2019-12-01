package com.example.jewell;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;


public class BottomBarActivity extends AppCompatActivity {

    private ProductRecyclerViewAdapter productAdapter;
    private StoreRecyclerViewAdapter storeAdapter;
    private String TAG = "BottomBarActivity";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "changed another item" + item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> fragList = new ArrayList<>();
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        fragList.add(recyclerView);
        fragList.add(new ProductsRecyclerViewFragment());
        fragList.add(new StoresRecyclerViewFragment(getSupportFragmentManager()));
        fragList.add(new InventorizationRecyclerViewFragment(getSupportFragmentManager()));
//        fragList.add(ScreenSlidePageFragment.newInstance("Invent", R.color.red_inactive));
//        fragList.add(ScreenSlidePageFragment.newInstance("Search", R.color.blue_inactive));
//        fragList.add(ScreenSlidePageFragment.newInstance("Likes", R.color.blue_grey_inactive));
//        fragList.add(ScreenSlidePageFragment.newInstance("Notification", R.color.green_inactive));
//        fragList.add(ScreenSlidePageFragment.newInstance("Profile", R.color.purple_inactive));
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(fragList, getSupportFragmentManager(), getLifecycle());

        final BubbleNavigationLinearView bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
        bubbleNavigationLinearView.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));

        bubbleNavigationLinearView.setBadgeValue(0, "40");
        bubbleNavigationLinearView.setBadgeValue(1, null); //invisible badge
        bubbleNavigationLinearView.setBadgeValue(2, "7");
//        bubbleNavigationLinearView.setBadgeValue(3, "2");
//        bubbleNavigationLinearView.setBadgeValue(4, ""); //empty badge

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
                Log.d("TAAG", "page was selected");
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

//        initRecyclerView(this);
//        addDataSet();
    }
//    private void addDataSet() {
//        List<Product> data = DataSource.Companion.createDataSet();
//        productAdapter.submitList(data);
//    }
//
//    private void initRecyclerView(Context context) {
//        Log.d(TAG, "initRecyclerView: init recyclerview.");
//        productAdapter = new ProductRecyclerViewAdapter(context);
//        TopSpacingItemDecoration topSpacingItemDecoration = new TopSpacingItemDecoration(30);
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(productAdapter);
//        recyclerView.addItemDecoration(topSpacingItemDecoration);
//
//        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
//    }

}