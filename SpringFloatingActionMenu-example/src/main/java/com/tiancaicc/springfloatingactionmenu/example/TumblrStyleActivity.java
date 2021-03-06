package com.tiancaicc.springfloatingactionmenu.example;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.MenuItemView;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

public class TumblrStyleActivity extends AppCompatActivity implements View.OnClickListener{

    private static int[] frameAnimRes = new int[]{
            R.mipmap.compose_anim_1,
            R.mipmap.compose_anim_2,
            R.mipmap.compose_anim_3,
            R.mipmap.compose_anim_4,
            R.mipmap.compose_anim_5,
            R.mipmap.compose_anim_6,
            R.mipmap.compose_anim_7,
            R.mipmap.compose_anim_8,
            R.mipmap.compose_anim_9,
            R.mipmap.compose_anim_10,
            R.mipmap.compose_anim_11,
            R.mipmap.compose_anim_12,
            R.mipmap.compose_anim_13,
            R.mipmap.compose_anim_14,
            R.mipmap.compose_anim_15,
            R.mipmap.compose_anim_15,
            R.mipmap.compose_anim_16,
            R.mipmap.compose_anim_17,
            R.mipmap.compose_anim_18,
            R.mipmap.compose_anim_19
    };

    private SpringFloatingActionMenu springFloatingActionMenu;
    private int frameDuration = 20;
    private AnimationDrawable frameAnim;
    private AnimationDrawable frameReverseAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumblr);

        createFabFrameAnim();
        createFabReverseFrameAnim();

        findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TumblrStyleActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        final FloatingActionButton fab = new FloatingActionButton(this);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setImageDrawable(frameAnim);
//        fab.setImageResource(android.R.drawable.ic_dialog_email);
        fab.setColorPressedResId(R.color.colorPrimary);
        fab.setColorNormalResId(R.color.fab);
        fab.setColorRippleResId(R.color.text_color);
        fab.setShadow(true);
        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(this)
                .fab(fab)
                .addMenuItem(R.color.photo, R.mipmap.ic_messaging_posttype_photo, "Photo", R.color.text_color,this)
                .addMenuItem(R.color.chat, R.mipmap.ic_messaging_posttype_chat, "Chat", R.color.text_color,this)
                .addMenuItem(R.color.quote, R.mipmap.ic_messaging_posttype_quote, "Quote", R.color.text_color,this)
                .addMenuItem(R.color.link, R.mipmap.ic_messaging_posttype_link, "Link", R.color.text_color,this)
                .addMenuItem(R.color.audio, R.mipmap.ic_messaging_posttype_audio, "Audio", R.color.text_color,this)
                .addMenuItem(R.color.text, R.mipmap.ic_messaging_posttype_text, "Text", R.color.text_color,this)
                .addMenuItem(R.color.video, R.mipmap.ic_messaging_posttype_video, "Video", R.color.text_color,this)
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                .revealColor(R.color.colorPrimary)
                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        fab.setImageDrawable(frameAnim);
                        frameReverseAnim.stop();
                        frameAnim.start();
                    }

                    @Override
                    public void onMenuClose() {
                        fab.setImageDrawable(frameReverseAnim);
                        frameAnim.stop();
                        frameReverseAnim.start();
                    }
                })
                .build();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("SpringFloatingActionMenu");
    }

    private void createFabFrameAnim() {
        frameAnim = new AnimationDrawable();
        frameAnim.setOneShot(true);
        Resources resources = getResources();
        for (int res : frameAnimRes) {
            frameAnim.addFrame(resources.getDrawable(res), frameDuration);
        }
    }

    private void createFabReverseFrameAnim() {
        frameReverseAnim = new AnimationDrawable();
        frameReverseAnim.setOneShot(true);
        Resources resources = getResources();
        for (int i = frameAnimRes.length - 1; i >= 0; i--) {
            frameReverseAnim.addFrame(resources.getDrawable(frameAnimRes[i]), frameDuration);
        }
    }

    @Override
    public void onBackPressed() {
        if(springFloatingActionMenu.isMenuOpen()){
            springFloatingActionMenu.hideMenu();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.bloom_style:
                startActivity(new Intent(this,BloomStyleActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Log.d("TAG eg","onclick");
        MenuItemView menuItemView = (MenuItemView) v;
        Toast.makeText(this,menuItemView.getLabelTextView().getText(),Toast.LENGTH_SHORT).show();
    }
}
