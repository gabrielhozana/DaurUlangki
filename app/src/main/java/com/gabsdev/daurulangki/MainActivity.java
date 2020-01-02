package com.gabsdev.daurulangki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Pengambilan data, apakah ada yang login.
        mAuth = FirebaseAuth.getInstance();

        //Pengecekan, jika tidak ada login. Di arahkan ke Signin activity.
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user == null){
            startActivity(new Intent(MainActivity.this, SigninActivity.class));
            finish();
        }

//        btnLogout = findViewById(R.id.btnLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.signOut();
//                startActivity(new Intent(MainActivity.this, SigninActivity.class));
//                finish();
//            }
//        });

        // inisialisasi BottomNavigaionView
        bottomNavigationView = findViewById(R.id.bnNavbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelecteListener);

        Bundle intent = getIntent().getExtras();
        if (intent != null){
            String publisher = intent.getString("publisherid");

            SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
            editor.putString("profileid", publisher);
            editor.apply();

            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new AccountFragment()).commit();

        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new HomeFragment()).commit();

        }


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelecteListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.home_menu:
                    fragment = new HomeFragment();
                    break;

                case R.id.search_menu:
                    fragment = new SearchFragment();
                    break;

                case R.id.add_menu:
                    fragment = null;
                    startActivity(new Intent(MainActivity.this, PostActivity.class));
                    break;

                case R.id.notification_menu:
                    fragment = new NotificationFragment();
                    break;

                case R.id.account_menu:
                    SharedPreferences.Editor editor = getSharedPreferences("Account", MODE_PRIVATE).edit();
                    editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    editor.apply();
                    fragment = new AccountFragment();
                    break;

            }
            if (fragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer,fragment).commit();
            }
            return true;
        }
    };
}


