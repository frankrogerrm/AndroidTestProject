package net.morph.test.androidtestproject.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.morph.test.androidtestproject.R;
import net.morph.test.androidtestproject.beans.models.category.Category;
import net.morph.test.androidtestproject.interfaces.RecyclerViewClickListener;
import net.morph.test.androidtestproject.tasks.GetImageTask;

import java.util.ArrayList;

/**
 * Created by frank.ramos on 01/07/2015.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    private ArrayList<Category> categoryList;
    private Context context;
    private static RecyclerViewClickListener itemListener;


    public CategoryListAdapter(ArrayList<Category> categoryList,Context context,RecyclerViewClickListener itemListener){
        this.categoryList = categoryList;
        this.context=context;
        this.itemListener = itemListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryViewHolder categoryViewHolder= new CategoryViewHolder(v);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.category_item_name.setText(categoryList.get(position).name);
        if(categoryList.get(position).img_path!=null)
        {
            new GetImageTask(holder.category_item_image,context).execute(context.getResources().getString(R.string.imageUrlCategoryPath)+categoryList.get(position).img_path);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView category_item_cardview;
        ImageView category_item_image;
        TextView category_item_name;
        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getPosition());
        }

        CategoryViewHolder(View itemView) {
            super(itemView);
            category_item_cardview = (CardView)itemView.findViewById(R.id.category_item_cardview);
            category_item_image = (ImageView)itemView.findViewById(R.id.category_item_image);
            category_item_name = (TextView)itemView.findViewById(R.id.category_item_name);
            itemView.setOnClickListener(this);

        }
    }
}
