package com.example.dell.movieexplorationsystem;

/**
 * Created by Dell on 2/28/2017.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Dell on 2/26/2017.
 */
public class CustomAdapter extends ArrayAdapter<Movie> implements View.OnClickListener{

    private ArrayList<Movie> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView moviename;
        TextView rating;
        Button ratingBar;
    }

    public CustomAdapter(ArrayList<Movie> data, Context context) {
        super(context, R.layout.custom_list, data);
        this.dataSet = data;
        this.mContext=context;

    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Movie getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);

    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Movie dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list, parent, false);
            viewHolder.moviename = (TextView) convertView.findViewById(R.id.moviename);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            viewHolder.ratingBar = (Button) convertView.findViewById(R.id.rate);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.moviename.setText(dataModel.movieName);
        viewHolder.rating.setText(Float.toString(dataModel.rating));
        viewHolder.ratingBar.setText(dataModel.userRating);
        viewHolder.ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"1", "2", "3", "4", "5"};

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Give Rating");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        dataModel.userRating = items[item].toString();
                        notifyDataSetChanged();
                        //int position = (int)v.getTag();
                    }
                });
                //convertView.setTag(viewHolder);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        // Return the completed view to render on screen
        return convertView;
    }
}
