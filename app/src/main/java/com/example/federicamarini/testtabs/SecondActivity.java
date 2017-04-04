package com.example.federicamarini.testtabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class SecondActivity extends AppCompatActivity implements testsCallback {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {R.mipmap.ic_ping, R.mipmap.ic_download, R.mipmap.ic_upload,
            R.mipmap.ic_web, R.mipmap.ic_video, R.mipmap.ic_results};
    private SaveCSV saveCSV = new SaveCSV();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        try {
            setupViewPager(viewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }

    private void setupViewPager(ViewPager viewPager) throws Exception {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PingFragment());
        adapter.addFragment(new DownloadFragment());
        adapter.addFragment(new UploadFragment());
        adapter.addFragment(new WebFragment());
        adapter.addFragment(new VideoFragment());
        adapter.addFragment(new ResultFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void getRisultatiPing(int[] array) {
        for(int i = 0; i<array.length; i++) {
            Log.d("Seconda Activity", "Ping: " + array[i]);
        }
        saveCSV.salvaRisultati(array);
    }

    @Override
    public void getRisultatiDownload(int[] array) {
        for (int i = 0; i< array.length; i++){
            Log.d("Seconda Activity", "Download: "+array[i]);
        }
        saveCSV.salvaRisultati(array);
    }

    @Override
    public void getRisultatiUpload(int[] array) {
        for (int i=0; i<array.length; i++){
            Log.d("Seconda Activity", "Upload: "+array[i]);
        }
        saveCSV.salvaRisultati(array);
    }

    @Override
    public void getRisultatoWeb(int a) {
        Log.d("Seconda Activity", "Web: "+a);
        saveCSV.salvaRisultati(new int[] {a});
    }

    @Override
    public void getFirstFrame(int a) {
        Log.d("Seconda Activity", "Video: "+a);
        saveCSV.salvaRisultati(new int[] {a});
    }

    @Override
    public void getRisultatiVideo(int[] array) {
        for (int i=0; i<array.length; i++){
            Log.d("Seconda Activity", "Video Buffer: "+array[i]);
        }
        saveCSV.salvaRisultati(array);
    }
}
