package com.luobo.wanandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luobo.wanandroid.ui.home.HomeFragment;
import com.luobo.wanandroid.ui.home.HomeViewModel;
import com.luobo.wanandroid.ui.user.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView toolbarMainText;
    NavController navController;
    ImageView user;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AqFragment());
        fragments.add(new HomeFragment());
        fragments.add(new OtherFragment());

        user = findViewById(R.id.userImageAtBar);
        toolbarMainText = findViewById(R.id.toolbarMainText);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                toolbarMainText.setText(destination.getLabel());
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            viewModel.loginUser();
            }
        });
    }


}