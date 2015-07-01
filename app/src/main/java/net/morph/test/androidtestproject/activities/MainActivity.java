package net.morph.test.androidtestproject.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.fragments.categorylist.CategoryListFragment;

public class MainActivity extends AppCompatActivity implements CategoryListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }
}
