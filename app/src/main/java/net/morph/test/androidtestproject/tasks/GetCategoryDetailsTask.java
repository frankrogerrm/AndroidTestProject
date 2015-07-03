package net.morph.test.androidtestproject.tasks;

import android.content.Context;
import android.os.AsyncTask;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.beans.models.categorydetails.CategoryDetails;
import net.morph.test.androidtestproject.connection.ServiceMethodsInterface;

import java.util.ArrayList;

import retrofit.RestAdapter;

/**
 * Created by frank.ramos on 01/07/2015.
 */
public class GetCategoryDetailsTask extends AsyncTask<Void,Void,CategoryDetails>
{
    public AsyncTaskInterface asyncTaskInterface=null;
    private Context context;
    private RestAdapter restAdapter;
    private String URL;
    private String id;
    public GetCategoryDetailsTask(Context context,AsyncTaskInterface asyncTaskInterface,String id)
    {
        this.URL=new String();
        this.id=new String();
        this.URL=context.getResources().getString(R.string.serviceGetCategory);
        this.id=id;
        this.context=context;
        this.asyncTaskInterface=asyncTaskInterface;
    }
    @Override
    protected void onPreExecute() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .build();
    }
    @Override
    protected CategoryDetails doInBackground(Void... params) {
        ServiceMethodsInterface methods = restAdapter.create(ServiceMethodsInterface.class);
        return methods.getCategoryDetails(id);
    }
    @Override
    protected void onPostExecute(CategoryDetails categoryDetails) {
        asyncTaskInterface.getReturnObject(categoryDetails);
    }
}