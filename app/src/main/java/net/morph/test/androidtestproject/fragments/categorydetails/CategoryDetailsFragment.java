package net.morph.test.androidtestproject.fragments.categorydetails;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.activities.MainActivity;
import net.morph.test.androidtestproject.adapters.SubcategoryListAdapter;
import net.morph.test.androidtestproject.beans.models.categorydetails.CategoryDetails;
import net.morph.test.androidtestproject.beans.models.categorydetails.Subcategory;
import net.morph.test.androidtestproject.interfaces.RecyclerViewClickListener;
import net.morph.test.androidtestproject.tasks.AsyncTaskInterface;
import net.morph.test.androidtestproject.tasks.GetCategoryDetailsTask;
import net.morph.test.androidtestproject.tasks.GetImageTask;

import java.util.ArrayList;


public class CategoryDetailsFragment extends Fragment {

    private RecyclerView fragment_subcategory_list_recycler_view;
    private SubcategoryListAdapter subcategoryListAdapter;
    private ArrayList<Subcategory> subcategoryList;
    private CategoryDetails categoryDetails;
    private ImageView fragment_category_details_imageview;

    private String categoryId="1";


    public void mapControls(View view)
    {
        fragment_subcategory_list_recycler_view=(RecyclerView)view.findViewById(R.id.fragment_subcategory_list_recycler_view);
        fragment_subcategory_list_recycler_view.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragment_subcategory_list_recycler_view.setLayoutManager(layoutManager);
        fragment_category_details_imageview=(ImageView)view.findViewById(R.id.fragment_category_details_imageview);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_category_details, container, false);
        mapControls(view);


        final Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar);




        GetCategoryDetailsTask getCategoryDetailsTask=new GetCategoryDetailsTask(getActivity(), new AsyncTaskInterface() {
            @Override
            public void getReturnObject(Object object) {
                categoryDetails=new CategoryDetails();
                categoryDetails=(CategoryDetails)object;
                subcategoryList=new ArrayList<Subcategory>();
                RecyclerViewClickListener itemListener=new RecyclerViewClickListener() {
                    @Override
                    public void recyclerViewListClicked(View v, int position) {

                    }
                };
                subcategoryList=new ArrayList<Subcategory>();
                for(int i=0;i<categoryDetails.subcategory.length;i++)
                {
                    subcategoryList.add(categoryDetails.subcategory[i]);
                }

                subcategoryListAdapter=new SubcategoryListAdapter(subcategoryList,getActivity(),itemListener);
                fragment_subcategory_list_recycler_view.setAdapter(subcategoryListAdapter);
                new GetImageTask(fragment_category_details_imageview,getActivity()).execute(getActivity().getResources().getString(R.string.imageUrlCategoryPath) + categoryDetails.img_path);
                collapsingToolbar.setTitle(categoryDetails.name);
            }
        },this.getCategoryId());
        getCategoryDetailsTask.execute();


        return view;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
