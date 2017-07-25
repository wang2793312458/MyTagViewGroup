package com.dreamlive.mytagviewgroup.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dreamlive.mytagviewgroup.R;


/**
 * author: shell
 * date 2016/12/30 下午3:45
 **/
public class ImageLoader {

    public static void loadImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
