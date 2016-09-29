package com.wilddog.wilddogsyncdemo.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wilddog.client.Query;
import com.wilddog.wilddogsyncdemo.R;
import com.wilddog.wilddogsyncdemo.model.Comment;

/**
 * Created by Administrator on 2016/9/29.
 */
public class CommentAdapter extends WilddogListAdapter<Comment> {


    public CommentAdapter(Query ref, Activity activity, int layout) {
        super(ref, Comment.class, layout, activity);

    }

    /**
     * @param mRef        The Wilddog location to watch for data changes. Can also be a slice of a location, using some
     *                    combination of <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>,
     * @param mModelClass Wilddog will marshall the data at a location into an instance of a class that you provide
     * @param mLayout     This is the mLayout used to represent a single list item. You will be responsible for populating an
     *                    instance of the corresponding view with the data from an instance of mModelClass.
     * @param activity    The activity containing the ListView
     */
    public CommentAdapter(Query mRef, Class mModelClass, int mLayout, Activity activity) {
        super(mRef, mModelClass, mLayout, activity);
    }

    @Override
    protected void populateView(View v, Comment comment) {
        TextView authorText = (TextView) v.findViewById(R.id.tv_comment_user);
        TextView content = (TextView) v.findViewById(R.id.tv_comment_content);
        authorText.setText(comment.getPresenter());
        content.setText(comment.getContent());
    }
}
