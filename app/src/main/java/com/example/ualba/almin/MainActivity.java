package com.example.ualba.almin;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.amazonaws.mobile.config.AWSConfiguration;
        import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;

public class MainActivity extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        setContentView(R.layout.activity_main);
    }
}
