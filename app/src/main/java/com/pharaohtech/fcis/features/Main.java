package com.pharaohtech.fcis.features;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.pharaohtech.fcis.announcements.AnnouncementFragment;
import com.pharaohtech.fcis.R;
import com.pharaohtech.fcis.account.LoginActivity;
import com.pharaohtech.fcis.chat.ChatFragment;
import com.pharaohtech.fcis.controls.FirebaseMethods;
import com.pharaohtech.fcis.controls.InterfaceUpdater;
import com.pharaohtech.fcis.models.User;
import com.pharaohtech.fcis.profile.ProfileFragment;
import com.pharaohtech.fcis.questions.QuestionFragment;
import com.pharaohtech.fcis.results.ResultFragment;
import com.pharaohtech.fcis.tasks.TaskFragment;
import com.squareup.picasso.Picasso;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "Main";
    private Fragment navigatedFragment;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView name;
    private TextView email;
    private CircularImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseMethods firebaseMethods = new FirebaseMethods(Main.this);
        TextView name = findViewById(R.id.headerName);
        TextView email = findViewById(R.id.headerMail);
        CircularImageView picture = findViewById(R.id.headerPic);
        firebaseMethods.retrieveUserData(mAuth.getUid(), this);
        checkCurrentUser(mAuth.getCurrentUser());
        setContentView(R.layout.activity_main);
        navigatedFragment = new AnnouncementFragment();
        setFragment(navigatedFragment);
        initializeNavigationTools();
        navigationView.setCheckedItem(R.id.nav_announcement);
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                setUpWidgets(mAuth);
//            }
//        }, 2000);
    }

    public void setUpWidgets(String name, String email, String photo){
        TextView drawerName = findViewById(R.id.headerName);
        TextView drawerMail = findViewById(R.id.headerMail);
        CircularImageView drawerPicture = findViewById(R.id.headerPic);
        drawerName.setText(name);
        drawerMail.setText(email);
        Picasso.get().load(photo).into(drawerPicture);
    }

    //-------------------------------------------ToolbarAndDrawer-----------------------------------
    //==============================================================================================

    private void initializeNavigationTools(){
        //Toolbar and Drawer
        Toolbar toolbar = findViewById(R.id.topToolBar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment announcement = new AnnouncementFragment();
        Fragment tasks = new TaskFragment();
        Fragment questions = new QuestionFragment();
        Fragment resutls = new ResultFragment();
        Fragment chat = new ChatFragment();
        Fragment profile = new ProfileFragment();
        TextView title = findViewById(R.id.topBarTitle);
        switch (menuItem.getItemId()){
            case R.id.nav_announcement: setFragment(announcement);
                navigationView.setCheckedItem(R.id.nav_announcement);
                title.setText("Announcements");
                Toast.makeText(this, FirebaseMethods.loginUser.getDisplay_name(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_tasks: setFragment(announcement);
                navigationView.setCheckedItem(R.id.nav_tasks);
                title.setText("Tasks");
                break;
            case R.id.nav_QandA: setFragment(questions);
                navigationView.setCheckedItem(R.id.nav_QandA);
                title.setText("Q&A");
                break;
            case R.id.nav_results: setFragment(resutls);
                navigationView.setCheckedItem(R.id.nav_results);
                title.setText("Results");
                break;
            case R.id.nav_Chat: setFragment(chat);
                navigationView.setCheckedItem(R.id.nav_Chat);
                title.setText("Chat");
                break;
            case R.id.nav_Profile: setFragment(profile);
                navigationView.setCheckedItem(R.id.nav_Profile);
                title.setText("Profile");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void checkCurrentUser(FirebaseUser user){

        if(user == null) {
            Intent intent = new Intent(Main.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(!user.isEmailVerified()){
            Intent intent = new Intent(Main.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
