package freelifer.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlCalculatorBody;
    private EditText edNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        initView();
        Calculator.create(app.getScreenPoint()).fillCalculatorBody(this, rlCalculatorBody, edNumber);
    }

    private void initView() {
        rlCalculatorBody = find(R.id.rl_calculator_body);
        edNumber = find(R.id.ed_number);
        edNumber.setInputType(InputType.TYPE_NULL);
        edNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
    }

    @SuppressWarnings("unchecked")
    private <T> T find(int id) {
        return (T) findViewById(id);
    }
}
