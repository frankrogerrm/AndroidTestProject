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
public class GetCategoryDetailsTask extends AsyncTask<Void,Void,ArrayList<CategoryDetails>>
{
    public AsyncTaskInterface asyncTaskInterface=null;
    private Context context;
    private RestAdapter restAdapter;
    private String URL;
    public GetCategoryDetailsTask(Context context,AsyncTaskInterface asyncTaskInterface)
    {
        URL=new String();
        URL=context.getResources().getString(R.string.serviceGetCategory);
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
    protected ArrayList<CategoryDetails> doInBackground(Void... params) {
        ServiceMethodsInterface methods = restAdapter.create(ServiceMethodsInterface.class);
        return methods.getCategoryDetails("1");
    }

    @Override
    protected void onPostExecute(ArrayList<CategoryDetails> categoryDetails) {
        asyncTaskInterface.getReturnObject(categoryDetails);
    }
}