package com.google.codelabs.mdc.java.shrine;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ItemDetailFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private ViewPager viewPager2;
    private ImageSliderAdapter imageSliderAdapter;
    private LinearLayout dotsLayout;
    private TextView[] mDots;
    private int scroll, oldposition;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.shr_product_detail, container, false);
        viewPager2 = view.findViewById(R.id.image_slider);
        imageSliderAdapter = new ImageSliderAdapter(getActivity());
        viewPager2.setAdapter(imageSliderAdapter);

        dotsLayout = view.findViewById(R.id.dotsLayout);

        addDots(0);

        viewPager2.setOnPageChangeListener(listener);

        final ChipGroup chipGroup = view.findViewById(R.id.color_chip_group);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = view.findViewById(checkedId);
                if (chip!=null) {
                    chip.setChipStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                    chip.setChipStrokeWidth(5);
                }
            }
        });

        return view;
    }

    private void addDots(int position) {

        mDots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i =0; i < mDots.length; i++) {
            mDots[i] = new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#9632"));
            mDots[i].setTextSize(10);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mDots[i].setTextColor(getActivity().getResources().getColor(R.color.productGridBackgroundColor, null));
            }
            mDots[i].setPadding(0,0,40,0);
            dotsLayout.addView(mDots[i]);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mDots[position].setTextColor(getActivity().getResources().getColor(R.color.textColorPrimary, null));
        }
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position==0)
                oldposition = position;
            else
                oldposition = scroll;

            scroll = position;
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 1) {
                if (oldposition <= scroll && scroll!=3) {
                    mDots[scroll].setPivotX(3f);
                    mDots[scroll].animate().scaleXBy(3.25f).setDuration(500);
                } else {
                    if (scroll!=0) {
                        mDots[scroll].setPivotX(0f);
                        mDots[scroll].animate().scaleXBy(-4.25f).setDuration(500);
                    }
                }
            }
            if (state==2 || state==0) {
                addDots(scroll);
            }
        }
    };

}
