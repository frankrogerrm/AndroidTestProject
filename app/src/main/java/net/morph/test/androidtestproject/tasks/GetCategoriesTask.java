package net.morph.test.androidtestproject.tasks;

import android.content.Context;
import android.os.AsyncTask;
import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.beans.models.Category;
import net.morph.test.androidtestproject.connection.ServiceMethodsInterface;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by frank.ramos on 30/06/2015.
 */
public class GetCategoriesTask extends AsyncTask<Void,Void,List<Category>>
{
    public AsyncTaskInterface asyncTaskInterface=null;
    private Context context;
    private RestAdapter restAdapter;
    private String URL;
    public GetCategoriesTask(Context context,AsyncTaskInterface asyncTaskInterface)
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
    protected List<Category> doInBackground(Void... params) {
        ServiceMethodsInterface methods = restAdapter.create(ServiceMethodsInterface.class);
        return methods.getCategories();
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        asyncTaskInterface.getReturnObject(categories);
    }
}