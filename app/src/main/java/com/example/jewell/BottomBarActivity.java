package com.example.jewell;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewell.adapter.ProductRecyclerViewAdapter;
import com.example.jewell.adapter.StoreRecyclerViewAdapter;
import com.example.jewell.models.Product;
import com.example.jewell.view_decorator.TopSpacingItemDecoration;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


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

        initRecyclerView(this);
        addDataSet();
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