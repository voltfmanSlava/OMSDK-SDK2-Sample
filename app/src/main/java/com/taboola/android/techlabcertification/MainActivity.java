package com.taboola.android.techlabcertification;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.taboola.android.utils.Logger;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.setLogLevel(Logger.DEBUG);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception ignore) {
            Log.w(TAG, "ActionBar does not exists");
        }
        showBackArrow(false);

        // Code to handle toolbar title and back arrow
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            int lastBackStackEntryCount = getSupportFragmentManager().getBackStackEntryCount() - 1;

            if (lastBackStackEntryCount < 0) {
                resetToolbarTitle();
                if (getSupportActionBar() != null) {
                    showBackArrow(false);
                }
            } else {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(getSupportFragmentManager().getBackStackEntryAt(lastBackStackEntryCount).getName());
                    showBackArrow(true);
                }
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MenuFragment()).commit();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMenuItemClicked(Fragment fragmentToOpen, String screenName) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragmentToOpen)
                    .addToBackStack(screenName)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBackArrow(boolean shouldShowBackButton) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(shouldShowBackButton);
            getSupportActionBar().setDisplayShowHomeEnabled(!shouldShowBackButton);
        }
    }

    private void resetToolbarTitle() {
        getSupportActionBar().setTitle(R.string.app_name);
    }
}
