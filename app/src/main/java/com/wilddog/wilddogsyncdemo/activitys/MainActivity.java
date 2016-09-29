package com.wilddog.wilddogsyncdemo.activitys;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.WilddogSync;
import com.wilddog.wilddogsyncdemo.R;
import com.wilddog.wilddogsyncdemo.adapters.CommentAdapter;
import com.wilddog.wilddogsyncdemo.model.Comment;
import com.wilddog.wilddogsyncdemo.tool.ToastTool;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SyncReference ref;

    private ListView mlvComments;

    private Button mBtnAddComment;

    private EditText mEdtObserver;
    private EditText mEdtCommentContent;

    private CommentAdapter mCommentAdapter;

    private ValueEventListener mConnectedListener;
    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setButtonListener();
    }

    private void initData() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        ref=WilddogSync.getInstance().getReference().child("messageboard");
    }

    private void setButtonListener() {
        mBtnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addComment();
            }
        });
    }


    private void addComment(){
        String observer = mEdtObserver.getText().toString().trim();
        String commentContent = mEdtCommentContent.getText().toString().trim();

        if(TextUtils.isEmpty(observer)){
            ToastTool.showToast(MainActivity.this,"请填写评论者");
            return;}
        if(TextUtils.isEmpty(commentContent)){
            ToastTool.showToast(MainActivity.this,"请填写评论内容");
            return;}

        ref.push().setValue(new Comment(observer,commentContent));
        mEdtObserver.setText("");
        mEdtCommentContent.setText("");
        hintKeyBoard();
    }

    private void hintKeyBoard(){
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void initView() {
        mlvComments= (ListView) findViewById(R.id.lv_comments);
        mBtnAddComment= (Button) findViewById(R.id.btn_add_comment);
        mEdtObserver= (EditText) findViewById(R.id.et_observer);
        mEdtCommentContent= (EditText) findViewById(R.id.et_comment_content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCommentAdapter = new CommentAdapter(ref.limitToLast(50), this, R.layout.adapter_comment);
        mlvComments.setAdapter(mCommentAdapter);
        mCommentAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mlvComments.setSelection(mCommentAdapter.getCount()-1);
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = ref.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(MainActivity.this, "Connected to Wilddog", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Disconnected from Wilddog", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(SyncError wilddogError) {
                // No-op
            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        ref.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mCommentAdapter.cleanup();
    }


}
