package com.example.dmichel.flickster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmichel.flickster.R;
import com.example.dmichel.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dmichel on 2/9/2018.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    // View lookup cache

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
    }
    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get The data Item for position
        Movie movie = getItem(position);

        ViewHolder viewHolder;

        // Check the existing view
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie,parent,false);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // find the image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.idMovieImage);
        // clear out image from convertView
        ivImage.setImageResource(0);

        //TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        //TextView tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);

        // into the template view.
        viewHolder.tvTitle.setText(movie.getOrginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        // Return the completed view to render on screen

        // Populate data
        //tvTitle.setText(movie.getOrginalTitle());
        //tvOverview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath())
                .transform(new RoundedCornersTransformation(10, 10)).into((ImageView) convertView.findViewById(R.id.idMovieImage));

        //Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
        // return the view
        return convertView;
    }
}
