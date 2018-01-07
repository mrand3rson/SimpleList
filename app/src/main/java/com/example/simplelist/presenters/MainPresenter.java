package com.example.simplelist.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.simplelist.R;
import com.example.simplelist.custom.SimpleAdapter;
import com.example.simplelist.entities.Photo;
import com.example.simplelist.entities.PhotoResponse;
import com.example.simplelist.entities.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.simplelist.MyApplication.getRetrofitService;

/**
 * Created by Andrei on 06.01.2018.
 */

public class MainPresenter implements IPresenter, IMain, IDetails {

    private final String API_KEY = "7615458-f105a4e02d539fb897f89d9d8";

    private ArrayList<Map<String,String>> doubleList;
    private ArrayList<Statement> statements;
    private ArrayList<Photo> photos;
    private RecyclerView rView;


    public MainPresenter() {

    }

    public String FindDescriptionByTitle(String title) {
        for (int i = 0; i< statements.size(); i++) {
            if (statements.get(i).title.equals(title)) {
                return statements.get(i).body;
            }
        }
        return "";
    }

    public void PrepareUi(Context context, RecyclerView rView) {
        this.rView = rView;
        LoadRecyclerData(context);
    }

    public void ClearMain() {
        this.rView = null;
    }

    private void LoadRecyclerData(Context context) {

        String photoQuery = "yellow flowers";

        Observable<ArrayList<Statement>> first = getRetrofitService()
                .loadList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<PhotoResponse> second = getRetrofitService()
                .loadImagesURLs(API_KEY,
                        photoQuery.replace(" ", "+"),
                        "photo",
                        true,
                        1,
                        100)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        first.subscribe(statements -> {
            this.statements = statements;
            second.subscribe(photoResponse -> {
                photos = photoResponse.list;
                doubleList = MakeList(statements, photos);
                FillRecyclerView(context);
            });
        });
    }

    private ArrayList<Map<String,String>> MakeList(ArrayList<Statement> first, ArrayList<Photo> second) {
        ArrayList<Map<String,String>> result = null;

        if (first.size() <= second.size()) {
            result = new ArrayList<>(first.size());

            for (int i = 0; i < first.size(); i++) {
                if (first.get(i) != null && second.get(i) != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put(second.get(i).url, first.get(i).title);
                    result.add(map);
                }
            }
        }
        return result;
    }

    private void FillRecyclerView(Context context) {
        SimpleAdapter adapter = new SimpleAdapter(context, R.layout.item_simple, doubleList);
        rView.setAdapter(adapter);
    }
}
