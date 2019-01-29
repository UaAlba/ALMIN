package com.example.ualba.almin;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;

        import com.amazonaws.auth.AWSCredentialsProvider;
        import com.amazonaws.mobile.client.AWSMobileClient;
        import com.amazonaws.mobile.client.AWSStartupHandler;
        import com.amazonaws.mobile.client.AWSStartupResult;
        import com.amazonaws.mobile.config.AWSConfiguration;
        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
        import com.amazonaws.models.nosql.NoteDO;
        import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class MainActivity extends AppCompatActivity {

    // Declare a DynamoDBMapper object
    DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();

        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();
    }

    public void createNews() {
        final NoteDO noteItem = new NoteDO();

        noteItem.setUserId("apple");

        noteItem.setTitle("TiTle");
        noteItem.setContent("This is the article content");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(noteItem);
                // Item saved
            }
        }).start();
    }

    public void readNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NoteDO noteItem = dynamoDBMapper.load(
                        NoteDO.class,
                        "apple",
                        "Article1");

                // Item read
                // Log.d("News Item:", newsItem.toString());
            }
        }).start();
    }

    public void updateNews() {
        final NoteDO noteItem = new NoteDO();

        noteItem.setUserId("apple");

        noteItem.setTitle("Title1");
        noteItem.setContent("This is the updated content.");

        new Thread(new Runnable() {
            @Override
            public void run() {

                dynamoDBMapper.save(noteItem);

                // Item updated
            }
        }).start();
    }

    public void deleteNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NoteDO noteItem = new NoteDO();

                noteItem.setUserId("apple");    //partition key

                noteItem.setTitle("Title1");  //range (sort) key

                dynamoDBMapper.delete(noteItem);

                // Item deleted
            }
        }).start();
    }


}

