package com.example.morelle.e4fi_android_hoot;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;
import com.example.morelle.e4fi_android_hoot.BDD.CheckElement;
import com.example.morelle.e4fi_android_hoot.BDD.CheckList;
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Depenses;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.Fragments.AboutFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.AlbumFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.BilletFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.BudgetFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.CheckListFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.ChecklElementFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.CreateBilletFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.DatePickerFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.OptionFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.TimelineFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.VoyageDetailFragment;
import com.example.morelle.e4fi_android_hoot.Fragments.VoyageFragment;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        VoyageFragment.VoyageFragmentListener,
        AlbumFragment.OnFragmentInteractionListener,
        OptionFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener,
        ChecklElementFragment.checkElementListener,
        TimelineFragment.TimeLineFragmentListener,
        BilletFragment.deleteBilletListener,
        VoyageDetailFragment.BudgetListener,
        VoyageDetailFragment.checkListListener,
        VoyageDetailFragment.TimeLineListener,
        DepenseDialog.DepenseListener,
        BudgetFragment.BudgetFragmentListerner,
        CheckListFragment.CheckListFragmentListener,
        CheckListDialog.AddCheckListeListener,
        TimelineFragment.createBilletListener,
        TimelineFragment.deleteVoyageListener,
        ChecklElementFragment.deleteCheckListeListener

{

    private RelativeLayout framelayout;
    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            framelayout = (RelativeLayout)findViewById(R.id.content_main);

            VoyageFragment fr = new VoyageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.add(R.id.content_main, fr);
            transaction.commit();

            SugarContext.terminate();

            SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
           // schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());

            SugarContext.init(getApplicationContext());
            schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());

            //fab.setVisibility(View.INVISIBLE);
            Bitmap icon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.amal);
            /*Voyage naples = new Voyage("Voyage à Naples","03/05/2017","Naples Octobre 2016","lol salut", ImageUtile.GetByteFromBitmap(icon));
            Voyage.save(naples);*/
            icon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.imgsf);

 /*           Voyage SF = new Voyage("Oh Yeah !","03/05/2017","SF Aout 2016 ","lol salut",ImageUtile.GetByteFromBitmap(icon));
            Voyage.save(SF);
            Billet b = new Billet("mon premier billet",  "salut c'est un test","03/03/2017", naples.getId());
            Billet.save(b);
            Billet b1 = new Billet("mon deuxième billet",  "salut c'est un test","03/03/2017", naples.getId());
            Billet.save(b1);
            Billet b2 = new Billet("mon troisième billet",  "salut c'est un test","03/03/2017", naples.getId());
            Billet.save(b2);
            CheckList c = new CheckList("Valise",naples.getId());
            CheckList.save(c);
            CheckElement e1 = new CheckElement("To be done", false, c.getId() );
            CheckElement.save(e1);
*/
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                                                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

        }


    public void getPosition()
    {
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria critere = new Criteria();
        critere.setAccuracy(Criteria.ACCURACY_FINE);
        String name = locationManager.getBestProvider(critere, true);
        LocationProvider provider = locationManager.getProvider(name);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED )
        {
            Location location = locationManager.getLastKnownLocation(provider.getName());
            double L = location.getLatitude();
            double l = location.getLongitude();
            Log.d("GPS", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
        }

    }


        @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_voyage) {
            switchFragment(new VoyageFragment());
        } else if (id == R.id.nav_album) {
            switchFragment(new AlbumFragment());
        } else if (id == R.id.nav_option) {
            switchFragment(new OptionFragment());
        } else if (id == R.id.nav_about) {
            switchFragment(new AboutFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchFragment(Fragment f ){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, f);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void switchFragmentInVoyage(Fragment f){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.switch_fragment, f);
        ft.addToBackStack(null);
        ft.commit();
    }



    @Override
    public void onItemClick(Voyage voyage_item) {
        db.setCurrentVoyage(voyage_item);
        switchFragment(new VoyageDetailFragment());
     
    }
    @Override
    public void onItemClick(Billet billet) {
        db.setCurrentBillet(billet);
        switchFragmentInVoyage(new BilletFragment());
    }
    @Override
    public void onItemClick(CheckList checkList) {
        db.setCurrentCheckList(checkList);
        switchFragmentInVoyage(new ChecklElementFragment());
    }

    @Override
    public void  onDepenseValidation(Depenses depense){
        Depenses.save(depense);
        BudgetFragment fragment = BudgetFragment.newInstance();
        fragment.updateContent(db.getCurrentVoyage().getId());
        switchFragmentInVoyage(fragment);
    }

    @Override
    public void  addCheckListe(CheckList checkList){
        CheckList.save(checkList);
        switchFragmentInVoyage(new CheckListFragment());
    }

    @Override
    public void onBudgetClick() {

        switchFragmentInVoyage(new BudgetFragment());
    }
    @Override
    public void createBillet() {

        switchFragment(new CreateBilletFragment());
    }
    @Override
    public void deleteVoyage() {
        Voyage v = db.getCurrentVoyage();
        VoyageFragment fragment = VoyageFragment.newInstance();
        switchFragment(new VoyageFragment());
        Voyage.delete(v);

    }
    @Override
    public void deleteBillet() {
        Billet b = db.getCurrentBillet();
        Billet.delete(b);
        TimelineFragment fragment = TimelineFragment.newInstance();
        fragment.updateContent(db.getCurrentVoyage().getId());
        switchFragmentInVoyage(fragment);


    }
    @Override
    public void deleteCheckListe() {
        CheckList c = db.getCurrentCheckList();
        VoyageFragment voyage = VoyageFragment.newInstance();
        switchFragment(voyage);
        CheckList.delete(c);

    }

    @Override
    public void onCheckClick() {

        switchFragmentInVoyage(new CheckListFragment());
    }


    @Override
    public void onTimeLineClick() {

        switchFragmentInVoyage(new TimelineFragment());
    }

    @Override
    public void onChecklistFragmentInteraction(Long checkListeId) {
        ChecklElementFragment fragment = ChecklElementFragment.newInstance();
        fragment.updateContent(checkListeId);
        switchFragmentInVoyage(fragment);
    }
    @Override
    public void onBudgetUpdate(Long voyageId) {
        BudgetFragment fragment = BudgetFragment.newInstance();
        fragment.updateContent(voyageId);
        switchFragmentInVoyage(fragment);

    }

    @Override
    public void onAlbumFragmentInteraction(Uri uri)
    {

    }

    @Override
    public  void onOptionFragmentInteraction(Uri uri)
    {

    }

    @Override
    public void onAboutFragmentInteraction(Uri uri)
    {

    }
    public void showDatePickerDialog(View v) {
        //switchToEditTransaction();
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                switchFragment(new OptionFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}

/*---------- Listener class to get coordinates ------------- */
/*

 class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {
        //editLocation.setText("");
        //pb.setVisibility(View.INVISIBLE);
        //Toast.makeText(
        //        getBaseContext(),
        //        "Location changed: Lat: " + loc.getLatitude() + " Lng: "
        //                + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);

*/
/*
        *//*

*/
/*------- To get city name from coordinates -------- *//*
*/
/*

        String cityName = null;
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                + cityName;
        editLocation.setText(s);
*//*

    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}*/
