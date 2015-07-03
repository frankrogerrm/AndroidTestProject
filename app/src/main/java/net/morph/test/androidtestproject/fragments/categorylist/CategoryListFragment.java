package net.morph.test.androidtestproject.fragments.categorylist;
import android.app.Activity;
import android.app.FragmentTransaction;
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
import net.morph.test.androidtestproject.fragments.categorydetails.CategoryDetailsFragment;
import net.morph.test.androidtestproject.interfaces.RecyclerViewClickListener;
import net.morph.test.androidtestproject.tasks.AsyncTaskInterface;
import net.morph.test.androidtestproject.tasks.GetCategoriesTask;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {
    private RecyclerView fragment_category_list_recycler_view;
    private CategoryListAdapter categoryListAdapter;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Category> categoryList;

    public void mapControls(View view)
    {
        fragment_category_list_recycler_view=(RecyclerView)view.findViewById(R.id.fragment_category_list_recycler_view);
        fragment_category_list_recycler_view.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragment_category_list_recycler_view.setLayoutManager(layoutManager);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category_list, container, false);
        mapControls(view);
        GetCategoriesTask getCategoriesTask=new GetCategoriesTask(getActivity(), new AsyncTaskInterface() {
            @Override
            public void getReturnObject(Object object) {
                categoryList=new ArrayList<Category>();
                categoryList=(ArrayList<Category>)object;
                RecyclerViewClickListener itemListener=new RecyclerViewClickListener() {
                    @Override
                    public void recyclerViewListClicked(View v, int position) {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        CategoryDetailsFragment categoryDetailsFragment =new CategoryDetailsFragment();
                        categoryDetailsFragment.setCategoryId(categoryList.get(position).id);
                        fragmentTransaction.replace(R.id.fragmentShown, categoryDetailsFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                };
                categoryListAdapter=new CategoryListAdapter((ArrayList<Category>)object,getActivity(),itemListener);
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