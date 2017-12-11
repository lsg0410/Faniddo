package com.example.yjkim.faniddo;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by yjkim on 2017-11-24.
 */

public class Drawer extends ActionBarDrawerToggle {
    RefreshCall mRefreshCall = null;

    public Drawer(Activity activity, DrawerLayout drawerLayout,
                                 Toolbar toolbar, @StringRes int openDrawerContentDescRes,
                                 @StringRes int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes,
                closeDrawerContentDescRes);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);

        mRefreshCall.refresh();
    }

    public void setRefreshCall(RefreshCall call) {
        mRefreshCall = call;
    }

    public interface RefreshCall {
        public void refresh();
    }
}
