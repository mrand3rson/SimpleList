package com.example.simplelist.services;

import com.example.simplelist.entities.PhotoResponse;
import com.example.simplelist.entities.Statement;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Andrei on 06.01.2018.
 */

public interface SimpleService {

    @GET("https://jsonplaceholder.typicode.com/posts") //100
    Observable<ArrayList<Statement>> loadList();

//    @GET("https://pixabay.com/api/" +
//            "?key=7615458-f105a4e02d539fb897f89d9d8" +
//            "&q=yellow+flowers" +
//            "&image_type=photo" +
//            "&pretty=true" +
//            "&page=1" +
//            "&per_page=100")
//    Observable<PhotoResponse> loadImagesURLs();
    @GET("https://pixabay.com/api")
    Observable<PhotoResponse> loadImagesURLs(@Query("key") String key,
                                             @Query("q") String query,
                                             @Query("image_type") String imageType,
                                             @Query("pretty") boolean pretty,
                                             @Query("page") int page,
                                             @Query("per_page") int perPage);

}
