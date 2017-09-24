package com.example.praveer.simulator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.hypertrack.lib.HyperTrack;
import com.hypertrack.lib.callbacks.HyperTrackCallback;
import com.hypertrack.lib.models.ErrorResponse;
import com.hypertrack.lib.models.SuccessResponse;
import com.hypertrack.lib.models.User;
import com.hypertrack.lib.models.UserParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HyperTrack.initialize(this.getApplicationContext(), "pk_54f2e023cd179babf0400dd029cbfff1463d4730");

        // To request Location Permissions
        HyperTrack.requestPermissions(this);

        // To enable Location Services
        HyperTrack.requestLocationServices(this, null);

        UserParams userParams = new UserParams().setName("Simulator1")
                .setPhone("123456")
                .setLookupId("123456");

        // This API will create a new user only if none exists already for the given lookup_id
        HyperTrack.getOrCreateUser(userParams, new HyperTrackCallback() {
            @Override
            public void onSuccess(@NonNull SuccessResponse successResponse) {
                if (successResponse.getResponseObject() != null) {
                    User user = (User) successResponse.getResponseObject();
                    // Handle user_id, if needed
                    String userId = user.getId();

                    //Toast.makeText(this, "User created successfully with UserId: " + userId, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(@NonNull ErrorResponse errorResponse) {
                // Handle createUser error here
                //Toast.makeText(this, errorResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        LatLng startLatLng = new LatLng(37.759418,122.4426787);

        HyperTrack.startMockTracking(startLatLng, new HyperTrackCallback() {
            @Override
            public void onSuccess(@NonNull SuccessResponse successResponse) {

            }

            @Override
            public void onError(@NonNull ErrorResponse errorResponse) {

            }
        });

    }
}
