package org.mym.featuredshowcase.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.mym.featuredshowcase.FeaturedShowcaseView;
import org.mym.featuredshowcase.ShowConfig;
import org.mym.featuredshowcase.shape.CircleShape;
import org.mym.featuredshowcase.shape.OvalShape;
import org.mym.featuredshowcase.shape.RectangleShape;
import org.mym.featuredshowcase.shape.RoundedRectShape;
import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.ViewTarget;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDemo = (Button) findViewById(R.id.button);
        btnDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showShowcaseView();
    }

    private void showShowcaseView(){
        //Show defaults
        ShowConfig config = new ShowConfig.Builder()
                .adjustToTarget(true)
                .maskColor(Color.parseColor("#99000000"))
                .hideOnTouchOutside(false)
                .shapePadding(new Padding(20))
                .target(new ViewTarget(btnDemo))
//                .targetDrawer(new CircleShape())
//                .targetDrawer(new RectangleShape())
                .targetDrawer(new RoundedRectShape(10))
//                .targetDrawer(new OvalShape())
                .showcaseListener(null)
                .build();
        FeaturedShowcaseView.show(this, config);
    }
}
