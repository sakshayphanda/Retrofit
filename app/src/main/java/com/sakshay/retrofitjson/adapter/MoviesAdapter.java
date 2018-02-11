package com.sakshay.retrofitjson.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sakshay.retrofitjson.R;
import com.sakshay.retrofitjson.model.MovieResultData;

import java.util.List;

/**
 * Created by aries on 02-02-2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieResultData> movies;
    private int rowLayout;
    private Context context;
    private MovieResultData item;


    public MoviesAdapter(List<MovieResultData> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public MoviesAdapter() {

    }


    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        holder.bind(position);
     //   holder.setIsRecyclable(false);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        Glide.with(context).load(context.getString(R.string.BASE_IMG_URL)+movies.get(position).getPosterPath()).into(holder.thumb);

        item = movies.get(position);

        holder.setOnClick(position);
        holder.setOnLongClick(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final View rootView;
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView thumb;
        private SparseBooleanArray itemStateArray= new SparseBooleanArray();

        public MovieViewHolder(View v) {
            super(v);
            rootView= v;
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            thumb = v.findViewById(R.id.poster);
        }


        public void setOnClick(final int position) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                   // boolean a = itemStateArray.get(adapterPosition, false);
                  //  Log.e("----"," "+!a);//retuns true
                    if (!itemStateArray.get(adapterPosition, false))
                    {//if(!false) i.e true (Item is set to be false in the SparseBooleanArray)
                        movieDescription.setMaxLines(Integer.MAX_VALUE);
                      //  Log.e("----"," "+a);
                        itemStateArray.put(adapterPosition, true);//click has been performed
                    }
                    else  {//if value is found or true
                        movieDescription.setMaxLines(3);
                        itemStateArray.put(adapterPosition, false);
                    }
                }
            });

            //for check boxes
            /*if (!itemStateArray.get(adapterPosition, false)) {
                mCheckedTextView.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            }
            else  {
                mCheckedTextView.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }*/
        }

        public void setOnLongClick(int position) {

            rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Toast.makeText(view.getContext(),"Longpress :"+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        public void bind(int position) {
            // use the sparse boolean array to check whether there is any position added in the array
            if (!itemStateArray.get(position, false))//if no positon found opened
                movieDescription.setMaxLines(3);
            else {
                    movieDescription.setMaxLines(Integer.MAX_VALUE);//if any position is opened.
            }
            /*
            for check boxes
            if (!itemStateArray.get(position, false)) {
                mCheckedTextView.setChecked(false);}
            else {
                mCheckedTextView.setChecked(true);
            }*/
        }
    }
}