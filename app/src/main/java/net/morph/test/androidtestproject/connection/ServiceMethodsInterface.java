package net.morph.test.androidtestproject.connection;

import net.morph.test.androidtestproject.beans.models.category.Category;
import net.morph.test.androidtestproject.beans.models.categorydetails.CategoryDetails;
import net.morph.test.androidtestproject.beans.rest.MainJson;


import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by frank.ramos on 30/06/2015.
 */
public interface ServiceMethodsInterface {

    @GET("/bins/26j6q")
    MainJson getMainJson(
            //@Query("api_key") String key
    );
    @GET("/archies/public/category")
    ArrayList<Category> getCategories();

    @GET("/archies/public/category/details/{id}")
    ArrayList<CategoryDetails> getCategoryDetails(@Query("id") String key)
    ;


}