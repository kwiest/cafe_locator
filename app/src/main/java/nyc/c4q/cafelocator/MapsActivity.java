package nyc.c4q.cafelocator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nyc.c4q.cafelocator.pojo.ResultsNear;
import nyc.c4q.cafelocator.presenter.MainPresenter;
import nyc.c4q.cafelocator.ui.DetailFragment;
import nyc.c4q.cafelocator.ui.ListFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        MainViewInterface, ListFragment.SelectedListener {

    @BindView(R.id.keyword)
    EditText keyword;

    @Inject
    MainPresenter presenter;

    private GoogleMap mMap;
    FragmentManager manager;
    private ListFragment listFragment = new ListFragment();
    private DetailFragment detailFragment = new DetailFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        setupMapAndListFrag();
        CafeLocatorApp application = (CafeLocatorApp) getApplicationContext();
        application.getMyComponent().inject(this);
        presenter.setmainViewInter(this);
    }

    private void setupMapAndListFrag() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.list_frag, listFragment);
        transaction.commit();
    }

    @OnClick(R.id.search_button)
    public void searchButtonClicked() {
        mMap.clear();
        presenter.getStoreList(keyword.getText().toString());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        // shift camera to users location
    }

    @Override
    public void listOfCafesFound(List<ResultsNear> resultsNear) {
        Log.e("Maps Size",resultsNear.size()+"");
        listFragment.updateArticleView(resultsNear);
    }

    @Override
    public void loadCafesPosition(LatLng temp, String name) {
        mMap.addMarker(new MarkerOptions().position(temp).title(name));
    }

    @Override
    public void sendKeywordLocation(LatLng location) {
        CameraPosition keywordLocation = CameraPosition.builder().target(location).zoom(11).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(keywordLocation));
    }

    @Override
    public void setRecyclePointer(int position) {
        listFragment.scrollToPosition(position);
    }

    @Override
    public void sendStore(ResultsNear resultsNear) {
        inflateDetailView();
        detailFragment.passStoreSelected(resultsNear);
    }

    @Override
    public void nothingFound() {
        Toast.makeText(this, getResources().getString(R.string.no_stores), Toast.LENGTH_SHORT).show();
    }

    private void inflateDetailView() {
        FragmentTransaction Detaittransaction = manager.beginTransaction();
        Detaittransaction.replace(R.id.container, detailFragment);
        Detaittransaction.addToBackStack("detail");
        Detaittransaction.commit();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        presenter.markerClicked(marker.getTitle());
        return false;
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ListFragment) {
            ListFragment listFragment = (ListFragment) fragment;
            listFragment.setOnHeadlineSelectedListener(this);
        }
    }

    @Override
    public void onItemViewSelected(int name) {
        presenter.findStoreByName(name);
    }
}