package com.example.material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        coordinatorLayout = findViewById(R.id.coordinator);
        tabLayout = findViewById(R.id.tabs);
      viewPager = findViewById(R.id.view_pager);

      viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

      tabLayout.setupWithViewPager(viewPager);

      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Snackbar.make(coordinatorLayout,"I am Snackbar",Snackbar.LENGTH_LONG).show();
          }
      });
      // TabLayout을 최상단으로 올려 클릭이 되게끔
      tabLayout.bringToFront();
      tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {
              viewPager.setCurrentItem(tab.getPosition());
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {

          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {

          }

      });

    }
}