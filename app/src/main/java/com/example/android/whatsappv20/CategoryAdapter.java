package com.example.android.whatsappv20;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    public CategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CameraFragment();
        } else if (position == 1) {
            return new ChatFragment();
        } else if (position == 2) {
            return new StatusFragment();
        } else {
            return new CallsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        // Declared mContext as global variable
        if (position == 0) {
//            return mContext.getString(R.string.cam);
            return null;
        } else if (position == 1) {
            return mContext.getString(R.string.chats);
        } else if (position == 2) {
            return mContext.getString(R.string.staus);
        } else {
            return mContext.getString(R.string.calls);
        }
    }

}
