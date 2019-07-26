package com.pharaohtech.fcis.announcements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.pharaohtech.fcis.R;
import com.pharaohtech.fcis.models.Post;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class PostsAdapter extends FirestoreRecyclerAdapter<Post, PostsAdapter.PostHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostsAdapter(@NonNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostHolder postHolder, int i, @NonNull Post post) {
        postHolder.textViewDisplay_name.setText(post.getName());
        Picasso.get().load(post.getProfilePhoto()).into(postHolder.circularImageViewProfile_Picture);
        postHolder.textViewCaption.setText(post.getCaption());
        postHolder.textViewTime.setText(new Date(post.getTimestamp()).toString());
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostHolder(v);
    }

    class PostHolder extends RecyclerView.ViewHolder{

        TextView textViewDisplay_name;
        TextView textViewCaption;
        TextView textViewTime;
        CircularImageView circularImageViewProfile_Picture;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            textViewDisplay_name = itemView.findViewById(R.id.recyclerProfileName);
            textViewCaption = itemView.findViewById(R.id.recyclerCaption);
            textViewTime = itemView.findViewById(R.id.recyclerTimeStamp);
            circularImageViewProfile_Picture = itemView.findViewById(R.id.recyclerProfilePicture);
        }
    }
}
