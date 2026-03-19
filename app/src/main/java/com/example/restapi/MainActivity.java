package com.example.restapi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.restapi.Fragment.HomeFragment;
import com.example.restapi.Fragment.ItemListFragment;
import com.example.restapi.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "mar";
    ActivityMainBinding b;

    HashMap<Integer, Fragment> fragmentMap;
    Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(b.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(b.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ Manifest.permission.INTERNET }, 1);
        }

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.nav_home, new HomeFragment());
        fragmentMap.put(R.id.nav_list, new ItemListFragment());

        if (savedInstanceState == null) {
            b.bottomNavigation.setSelectedItemId(R.id.nav_home);
            loadFragment(fragmentMap.get(R.id.nav_home));
        }

        b.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = fragmentMap.get(item.getItemId());
            return loadFragment(selectedFragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            var transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true);

            if (activeFragment != null) {
                transaction.hide(activeFragment);
            }

            if (!fragment.isAdded()) {
                transaction.add(R.id.main_content, fragment);
            } else {
                transaction.show(fragment);
            }

            transaction.commit();
            activeFragment = fragment;
            return true;
        }
        return false;
    }
}