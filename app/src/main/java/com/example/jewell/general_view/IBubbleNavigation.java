package com.example.jewell.general_view;

import android.graphics.Typeface;

import com.example.jewell.general_view.listener.BubbleNavigationChangeListener;


@SuppressWarnings("unused")
public interface IBubbleNavigation {
    void setNavigationChangeListener(BubbleNavigationChangeListener navigationChangeListener);

    void setTypeface(Typeface typeface);

    int getCurrentActiveItemPosition();

    void setCurrentActiveItem(int position);

    void setBadgeValue(int position, String value);
}
