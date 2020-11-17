package com.luobo.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luobo.wanandroid.ui.login.LoginActivity;
import com.luobo.wanandroid.ui.login.LoginViewModel;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    TextView toolbarMainText;
    NavController navController;
    ImageView user;
    LoginViewModel viewModel;
    TextView username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.userImageAtBar);
        username =findViewById(R.id.username);
        toolbarMainText = findViewById(R.id.toolbarMainText);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                toolbarMainText.setText(destination.getLabel());
            }
        });

        user.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }


}