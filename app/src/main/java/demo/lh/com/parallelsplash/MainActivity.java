package demo.lh.com.parallelsplash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /**
     * 容器类
     */
    private ParallelContainer container;
    private ImageView iv_man;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.layout_container);
        iv_man = findViewById(R.id.iv_man);
        iv_man.setBackgroundResource(R.drawable.man_run);
        /** 方便后面再次增加界面**/
        container.setUp(new int[]{
                R.layout.view_intro_1,
                R.layout.view_intro_2,
                R.layout.view_intro_3,
                R.layout.view_intro_4,
                R.layout.view_intro_5,
                R.layout.view_login

        });

        container.setIv_man(iv_man);
    }
}
