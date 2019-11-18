package com.example.jewell;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewell.adapter.ProductRecyclerViewAdapter;
import com.example.jewell.adapter.ScreenSlidePagerAdapter;
import com.example.jewell.fragment.ScreenSlidePageFragment;
import com.example.jewell.general_view.BubbleNavigationLinearView;
import com.example.jewell.models.Product;
import com.example.jewell.view_decorator.TopSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class BottomBarActivity extends AppCompatActivity {
    private ProductRecyclerViewAdapter productAdapter;
    private String TAG = "BottomBarActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareBottomView();
        initRecyclerView(this);
        addDataSet();
    }

    private void prepareBottomView() {
        ArrayList<ScreenSlidePageFragment> fragList = new ArrayList<>();
        fragList.add(ScreenSlidePageFragment.newInstance(getString(R.string.products), R.color.red_inactive));
        fragList.add(ScreenSlidePageFragment.newInstance(getString(R.string.inventarisation), R.color.blue_inactive));
        fragList.add(ScreenSlidePageFragment.newInstance(getString(R.string.archive), R.color.blue_grey_inactive));
        fragList.add(ScreenSlidePageFragment.newInstance(getString(R.string.fake), R.color.green_inactive));
        fragList.add(ScreenSlidePageFragment.newInstance(getString(R.string.mock), R.color.purple_inactive));
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(fragList, getSupportFragmentManager());

        final BubbleNavigationLinearView bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
        bubbleNavigationLinearView.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));

        bubbleNavigationLinearView.setBadgeValue(0, "40");
        bubbleNavigationLinearView.setBadgeValue(1, null); //invisible badge
        bubbleNavigationLinearView.setBadgeValue(2, "7");
        bubbleNavigationLinearView.setBadgeValue(3, "2");
        bubbleNavigationLinearView.setBadgeValue(4, ""); //empty badge
    }

    private void addDataSet() {
        List<Product> data = DataSource.Companion.createDataSet();
        productAdapter.submitList(data);
    }

    private void initRecyclerView(Context context) {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        productAdapter = new ProductRecyclerViewAdapter(context);
        TopSpacingItemDecoration topSpacingItemDecoration = new TopSpacingItemDecoration(30);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
        recyclerView.addItemDecoration(topSpacingItemDecoration);

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
    }

}