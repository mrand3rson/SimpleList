package com.example.simplelist.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.simplelist.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.example.simplelist.MyApplication.getPresenter;


@EActivity(R.layout.activity_main)
public class MainActivity extends ScrollOutActivity {

    @ViewById(R.id.recycler)
    RecyclerView rView;

    @AfterViews
    void onViewsReady() {
        Toast.makeText(this, "THERE", Toast.LENGTH_SHORT).show();

        rView.setLayoutManager(new LinearLayoutManager(this));
        getPresenter().PrepareUi(this, rView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getPresenter().ClearMain();
    }
}