package net.morph.test.androidtestproject.connection;

import net.morph.test.androidtestproject.beans.models.Category;
import net.morph.test.androidtestproject.beans.rest.MainJson;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by frank.ramos on 30/06/2015.
 */
public interface ServiceMethodsInterface {

    @GET("/bins/26j6q")
    MainJson getMainJson(
            //@Query("api_key") String key
    );
    @GET("/archies/public/category")
    List<Category> getCategories();


}