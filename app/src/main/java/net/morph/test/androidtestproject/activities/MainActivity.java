package net.morph.test.androidtestproject.activities;

import android.app.Activity;
import android.os.Bundle;
import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.beans.models.Category;
import net.morph.test.androidtestproject.beans.rest.MainJson;
import net.morph.test.androidtestproject.tasks.AsyncTaskInterface;
import net.morph.test.androidtestproject.tasks.GetCategoriesTask;
import net.morph.test.androidtestproject.tasks.MainJsonTask;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetCategoriesTask getCategoriesTask=new GetCategoriesTask(getApplicationContext(), new AsyncTaskInterface(){
            @Override
            public void getReturnObject(Object object) {
                List<Category> ttt=(List<Category>)object;
                int rrr=6;
                rrr=8;

            }
        });
        getCategoriesTask.execute();


    }

}
