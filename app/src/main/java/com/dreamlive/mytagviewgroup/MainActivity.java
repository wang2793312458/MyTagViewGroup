package com.dreamlive.mytagviewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dreamlive.mytagviewgroup.utils.DataRepo;
import com.dreamlive.mytagviewgroup.utils.TagViewGroup;
import com.dreamlive.mytagviewgroup.view.ITagView;
import com.dreamlive.mytagviewgroup.view.TagImageView;
import com.dreamlive.mytagviewgroup.view.TagTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CREATE_TAG = 0x001;

    private TagImageView mTagImageView;
    private Button mButton;
    private Button mListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        DataRepo.initData();
        mTagImageView = (TagImageView) findViewById(R.id.tagImageView);
        mButton = (Button) findViewById(R.id.transButton);
        mListBtn = (Button) findViewById(R.id.listBtn);
        mTagImageView.setImageUrl("http://47.92.74.33:8080/images/20170704/149914880849150557/1499148831159.jpg");
        mTagImageView.setTagGroupClickListener(mTagGroupClickListener);
        mTagImageView.setTagList(DataRepo.tagGroupList);
        mTagImageView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tagImageView:
                mTagImageView.excuteTagsAnimation();
                break;
            case R.id.transButton:
                startActivityForResult(new Intent(MainActivity.this, TagEditActivity.class), CREATE_TAG);
                break;
            case R.id.listBtn:
                startActivity(new Intent(MainActivity.this, TagListActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CREATE_TAG) {
            mTagImageView.setTagList(DataRepo.tagGroupList);
        }
    }

    private TagViewGroup.OnTagGroupClickListener mTagGroupClickListener = new TagViewGroup.OnTagGroupClickListener() {
        @Override
        public void onCircleClick(TagViewGroup container) {
            Toast.makeText(MainActivity.this, "点击中心圆", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTagClick(TagViewGroup container, ITagView tag, int position) {
            Toast.makeText(MainActivity.this, "点击Tag->" + ((TagTextView) tag).getText().toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongPress(final TagViewGroup group) {
            Toast.makeText(MainActivity.this, "点击中心圆", Toast.LENGTH_SHORT).show();
        }
    };
}
