package com.example.tutorial.materialdesign.Tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.tutorial.materialdesign.AnnouncementFragment;
import com.example.tutorial.materialdesign.ItemListFragment;
import com.example.tutorial.materialdesign.R;
import com.example.tutorial.materialdesign.SettingsFragment;

/**
 * Created by Home-PC on 11-Mar-17.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] titles = {"Announcement", "List", "Settings"};
    int[] icons = new int[] {R.mipmap.ic_announcement_black_48dp, R.mipmap.ic_list_black_48dp, R.mipmap.ic_settings_black_48dp};
    private int heightIcon;

    public MyAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
        double scale = context.getResources().getDisplayMetrics().density;
        heightIcon = (int) (24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if(position == 0){
            fragment = new AnnouncementFragment();
        } else if(position == 1) {
            fragment = new ItemListFragment();
        } else if(position == 2){
            fragment = new SettingsFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        fragment.getArguments();
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable drawable = mContext.getResources().getDrawable(icons[position]);
        drawable.setBounds(0,0,heightIcon,heightIcon);

        ImageSpan imageSpan = new ImageSpan(drawable);

        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //return (titles[position])

        return  (spannableString);

    }
}
