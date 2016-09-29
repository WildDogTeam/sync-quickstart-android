package com.wilddog.wilddogsyncdemo.model;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Comment {

    private String presenter;
    private String content;

    public Comment(String presenter, String content) {
        this.presenter = presenter;
        this.content = content;
    }

    public Comment(){

    }


    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
