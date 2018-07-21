package com.jaddev888gmail.pocketstock.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaddev888gmail.pocketstock.R;

import butterknife.ButterKnife;


public class PortfolioFragment extends Fragment {



    public PortfolioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.portfolio_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
