package com.example.ualba.almin;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;

        import com.amazonaws.mobile.client.AWSMobileClient;
        import com.amazonaws.mobile.client.AWSStartupHandler;
        import com.amazonaws.mobile.client.AWSStartupResult;
        import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
        import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;

public class MainActivity extends AppCompatActivity {

    public static PinpointManager pinpointManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AWSMobileClient.getInstance().initialize(this).execute();

        PinpointConfiguration config = new PinpointConfiguration(
                MainActivity.this,
                AWSMobileClient.getInstance().getCredentialsProvider(),
                AWSMobileClient.getInstance().getConfiguration()
        );
        pinpointManager = new PinpointManager(config);
        pinpointManager.getSessionClient().startSession();
        pinpointManager.getAnalyticsClient().submitEvents();

    }
}

