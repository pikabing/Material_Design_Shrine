package com.google.codelabs.mdc.java.shrine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class ImageSliderAdapter extends PagerAdapter {

    private Context context;

    public ImageSliderAdapter(Context context) {
        this.context = context;
    }

    private int[] images_array = {
      R.drawable.slider_1,
      R.drawable.slider_2,
      R.drawable.slider_3,
      R.drawable.slider_4
    };

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_slider,container, false);
        ImageView imageView = view.findViewById(R.id.slider_image);

        imageView.setImageResource(images_array[position]);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return images_array.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
