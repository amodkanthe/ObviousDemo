package com.example.obviousdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.obviousdemo.ui.ImageListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ImageListFragment.newInstance()).commit()
        supportFragmentManager.addOnBackStackChangedListener {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                 getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);// show back button
            } else {
                getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

}