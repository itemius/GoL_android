package info.itemius.gol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import info.itemius.gol.views.SquareGridView;

public class MainActivity extends Activity {

    private SquareGridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        gridview = (SquareGridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setNumColumns(20);
        gridview.setHorizontalSpacing(0);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (GameState.cells[position]) {
                    ((ImageView) v).setImageResource(R.drawable.white);
                    GameState.cells[position] = false;
                } else {
                    ((ImageView) v).setImageResource(R.drawable.red);
                    GameState.cells[position] = true;
                }
            }
        });

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //manual
//                GameState.nextState();
//                gridview.invalidateViews();

                //async 1
                final int delay = 300;
                if (GameState.started){
                    ((Button) v).setText(getString(R.string.button_start));
                    GameState.started = false;
                } else {
                    GameState.started = true;
                            ((Button) v).setText(getString(R.string.button_stop));
                    new Thread(new Runnable() {
                        public void run() {
                            while (GameState.started){
                                try {
                                    Thread.sleep(delay);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                GameState.nextState();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        gridview.invalidateViews();

                                    }
                                });

                            }
                        }
                    }).start();

                }
            }
        });

    }

}
