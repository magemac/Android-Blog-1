package com.com.blog.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.com.blog.model.Post;
import com.com.blog.service.PostService;
import com.com.blog.controller.dto.CMRespDto;

import com.com.blog.view.post.PostUpdateActivity;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// VIEW 와 통신하는 중개자
public class PostController {

    private static final String TAG = "PostController";
    private PostService postService = PostService.service;

    public Call<CMRespDto<List<Post>>> findAll(){
        return postService.findAll();
    }

    public Call<CMRespDto<Post>> findById(int postId){
        return postService.findById(postId);
    }


    // test
    public void initPost(){
        Log.d(TAG, "initPost: ");
        postService.initPost().enqueue(new Callback<CMRespDto<List<Post>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Post>>> call, Response<CMRespDto<List<Post>>> response) {
                CMRespDto<List<Post>> cm =  response.body();
                Log.d(TAG, "onResponse: title : "+cm.getData().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Post>>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    //글 수정 페이지로
    public void updateForm(Context mConext){
        Intent intent = new Intent(
                mConext,
                PostUpdateActivity.class
        );
        mConext.startActivity(intent);
        
    }

}
