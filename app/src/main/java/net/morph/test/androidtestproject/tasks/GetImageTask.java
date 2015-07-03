package net.morph.test.androidtestproject.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import net.morph.test.androidtestproject.R;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by frank.ramos on 01/07/2015.
 */
public class GetImageTask extends AsyncTask<String,String,Bitmap> {

    private ImageView imageView;
    private ProgressDialog dialog;
    private Context context;
    public GetImageTask(ImageView imageView,Context context)
    {
        this.imageView=imageView;
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        dialog=new ProgressDialog(context);
        dialog.setMessage(context.getResources().getString(R.string.loadingMessage));
        dialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urldisplay = strings[0];
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }
}
