package com.example.simplelist.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplelist.MyApplication;
import com.example.simplelist.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_detailed)
public class DetailsActivity extends ScrollOutActivity {

    @Extra
    String string;

    @Extra
    byte[] bytebitmap;

    @ViewById(R.id.text_details)
    TextView text;

    @ViewById(R.id.description)
    TextView description;

    @ViewById(R.id.image_details)
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void FillViews() {
        image.setImageBitmap(getBitmap());
        text.setText(string);

        description.setText(MyApplication.getPresenter().FindDescriptionByTitle(string));
    }

    private Bitmap getBitmap() {
        return BitmapFactory.decodeByteArray(bytebitmap, 0, bytebitmap.length);
    }
}
