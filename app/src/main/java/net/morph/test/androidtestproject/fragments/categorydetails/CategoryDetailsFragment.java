package net.morph.test.androidtestproject.fragments.categorydetails;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.activities.MainActivity;

public class CategoryDetailsFragment extends Fragment  implements View.OnClickListener{

    private CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_category_details, container, false);

        coordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinator);

        ((MainActivity)getActivity()).setSupportActionBar((Toolbar)view.findViewById(R.id.toolbar));
        ActionBar actionbar = ((MainActivity)getActivity()).getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));

        Button buttonSnack = (Button)view.findViewById(R.id.button_snack);
        buttonSnack.setOnClickListener(this);


        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_snack:
                Snackbar.make(coordinatorLayout, getString(R.string.snacked), Snackbar.LENGTH_SHORT)
                        .setAction(getString(R.string.more), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), getString(R.string.snack_more), Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            default:
                break;
        }
    }


}
