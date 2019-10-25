package com.li.gohome.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.li.gohome.R;
import com.li.gohome.adapter.HomePageAdapter;
import com.li.gohome.baen.HomeModel;
import com.li.gohome.presenter.MainPresenter;

public class HomePageActivity extends AppCompatActivity implements IMainView{
    HomePageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        MainPresenter presenter = new MainPresenter(this);
        presenter.getNetWorkInfo();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomePageAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void getHomeModel(HomeModel homeModel) {
        if(adapter != null){
            adapter.addData(homeModel);
        }
    }
}
