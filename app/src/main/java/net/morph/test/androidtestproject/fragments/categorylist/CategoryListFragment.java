package net.morph.test.androidtestproject.fragments.categorylist;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.adapters.CategoryListAdapter;
import net.morph.test.androidtestproject.beans.models.category.Category;
import net.morph.test.androidtestproject.tasks.AsyncTaskInterface;
import net.morph.test.androidtestproject.tasks.GetCategoriesTask;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {
    private ArrayList<Category> categoryList;
    private RecyclerView fragment_category_list_recycler_view;
    private CategoryListAdapter categoryListAdapter;
    private OnFragmentInteractionListener mListener;
    public void mapControls(View view)
    {
        fragment_category_list_recycler_view=(RecyclerView)view.findViewById(R.id.fragment_category_list_recycler_view);
        fragment_category_list_recycler_view.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragment_category_list_recycler_view.setLayoutManager(layoutManager);    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onFragmentInteraction(getActivity().getResources().getString(R.string.categoryListTitle));
        }
        View view=inflater.inflate(R.layout.fragment_category_list, container, false);
        mapControls(view);
        GetCategoriesTask getCategoriesTask=new GetCategoriesTask(getActivity(), new AsyncTaskInterface() {
            @Override
            public void getReturnObject(Object object) {
                categoryListAdapter=new CategoryListAdapter((ArrayList<Category>)object,getActivity());
                fragment_category_list_recycler_view.setAdapter(categoryListAdapter);
            }
        });
        getCategoriesTask.execute();
        return view;
    }
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String title);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
