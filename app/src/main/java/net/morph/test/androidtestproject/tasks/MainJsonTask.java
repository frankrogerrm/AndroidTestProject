package net.morph.test.androidtestproject.tasks;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.beans.rest.MainJson;
import net.morph.test.androidtestproject.connection.ServiceMethodsInterface;

import retrofit.RestAdapter;

/**
 * Created by frank.ramos on 30/06/2015.
 */
public class MainJsonTask extends AsyncTask<Void,Void,MainJson>
{
    public AsyncTaskInterface asyncTaskInterface=null;
    private Context context;
    private RestAdapter restAdapter;
    private String URL;
    public MainJsonTask(Context context,AsyncTaskInterface asyncTaskInterface)
    {
        URL=new String();
        URL=context.getResources().getString(R.string.serviceMainJson);
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
    protected MainJson doInBackground(Void... params) {
        ServiceMethodsInterface methods = restAdapter.create(ServiceMethodsInterface.class);
        MainJson mainJson = methods.getMainJson();

        return mainJson;
    }
    @Override
    protected void onPostExecute(MainJson mainJson) {
        asyncTaskInterface.getReturnObject(mainJson);
    }
}