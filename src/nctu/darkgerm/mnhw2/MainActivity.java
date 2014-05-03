package nctu.darkgerm.mnhw2;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        ((Button) view.findViewById(R.id.start_mark))
            .setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateMap();
                }
            });

        return view;
    } // PlaceholderFragment.onCreateView

    private void updateMap() {
        String latitude = ((EditText) view.findViewById(R.id.latitude)).getText().toString();
        String longitude = ((EditText) view.findViewById(R.id.longitude)).getText().toString();

        LatLng latlng = new LatLng(
            Double.parseDouble(latitude),
            Double.parseDouble(longitude)
        );

        MapFragment mFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap mMap = mFrag.getMap();

        mMap.addMarker(
            new MarkerOptions()
                .position(latlng)
                .title("(" + latitude + ", " + longitude + ")")
        );

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(latlng, 15);
        mMap.animateCamera(cu);
    } // updateMap
} // class PlaceholderFragment
