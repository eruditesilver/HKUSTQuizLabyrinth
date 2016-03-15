package group3.project.comp4521.ust.hk.hkustquizlabyrinth;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.GameConFig;
import Model.User;
import SupportTools.FileFetcher;


public class MainActivity extends ActionBarActivity {

    private GameConFig gameConFig;
    private User user;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.post(initialization);
    }

    Runnable initialization = new Runnable() {
        @Override
        public void run() {
            initConfig();
            initUser();
        }
    };

    private void initConfig() {
        try {
            gameConFig = FileFetcher.fetchFromFile("GameConfig.txt");
        } catch (FileNotFoundException e) {
            try {
                gameConFig = FileFetcher.fetchFromFile("http://domain.com/DefaultGameConfig.txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUser() {
        try {
            user = FileFetcher.fetchFromFile("User.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
