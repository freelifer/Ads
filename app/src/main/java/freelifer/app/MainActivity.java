package freelifer.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlCalculatorBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        initView();
        Calculator.create(app.getScreenPoint()).fillCalculatorBody(this, rlCalculatorBody);
    }

    private void initView() {
        rlCalculatorBody = find(R.id.rl_calculator_body);
    }

    @SuppressWarnings("unchecked")
    private <T> T find(int id) {
        return (T) findViewById(id);
    }
}
