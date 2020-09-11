package com.johnmbiya.gads.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
