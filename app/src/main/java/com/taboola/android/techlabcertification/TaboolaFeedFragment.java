package com.taboola.android.techlabcertification;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.utils.SdkDetailsHelper;

public class TaboolaFeedFragment extends Fragment {

    private TaboolaWidget mTaboolaWidget;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_taboola_feed, container, false);
        mTaboolaWidget = view.findViewById(R.id.taboola_widget_below_article);
        buildBelowArticleWidget(inflater.getContext());
        return view;
    }

    private void buildBelowArticleWidget(Context context) {
        mTaboolaWidget
                .setPublisher("sdk-tester-omsdk")
                .setPageType("article")
                .setPageUrl("https://blog.taboola.com")
                .setPlacement("Feed without video")
                .setMode("thumbs-feed-01")
                .setTargetType("mix")
                .setInterceptScroll(true);
        mTaboolaWidget.getLayoutParams().height = SdkDetailsHelper.getDisplayHeight(context);
        mTaboolaWidget.fetchContent();
    }
}
