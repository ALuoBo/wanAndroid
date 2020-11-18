package com.luobo.wanandroid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luobo.wanandroid.ui.login.LoginViewModel;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    TextView toolbarMainText;
    NavController navController;
    LoginViewModel viewModel;
    TextView username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        toolbarMainText = findViewById(R.id.toolbarMainText);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> toolbarMainText.setText(destination.getLabel()));

        //user.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }


}