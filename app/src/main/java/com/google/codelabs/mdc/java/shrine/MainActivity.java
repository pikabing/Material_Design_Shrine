package com.google.codelabs.mdc.java.shrine;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private NestedScrollView product_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        final FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        new CountDownTimer(700, 700) {
            @Override
            public void onTick(long l) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(product_grid, "translationY", 0);
                animator.setDuration(400);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animator);
                animator.start();
            }

            @Override
            public void onFinish() {
                transaction.commit();
            }
        }.start();
    }

    public void setUpToolbar(View view) {
        final Toolbar toolbar = view.findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        product_grid =  view.findViewById(R.id.product_grid);

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                this,
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.shr_branded_menu), // Menu open icon
                getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon

        view.findViewById(R.id.all_products).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setNavigationIcon(R.drawable.shr_branded_menu);
                navigateTo(new ProductGridFragment(), false);
            }
        });

        view.findViewById(R.id.featured).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setNavigationIcon(R.drawable.shr_branded_menu);
                navigateTo(new FeatureFragment(), false);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.e("id","" + id);
        return true;
    }

}
