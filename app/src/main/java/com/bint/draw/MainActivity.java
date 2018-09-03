package com.bint.draw;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bint.draw.view.BinView;
import com.bint.draw.view.LinBinView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<>();
    private ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        viewList.add(new LinBinView(this));
        viewList.add(new BinView(this));
    }


    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewAdapter(viewList);
        viewPager.setAdapter(adapter);
    }

}
