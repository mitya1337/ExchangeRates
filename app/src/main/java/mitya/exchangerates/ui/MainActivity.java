package mitya.exchangerates.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitya.exchangerates.domain.CurrencyRate;
import mitya.exchangerates.R;
import mitya.exchangerates.network.RestService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final RestService.FixerService restService = new RestService().getFixerService();

    @BindView(R.id.inputCurrency)
    EditText inputCurrency;
    @BindView(R.id.ratesList)
    RecyclerView ratesList;
    @BindView(R.id.sendRequest)
    Button sendRequest;

    private RatesAdapter adapter = new RatesAdapter();
    private Subscription subscription;

    @Override
    protected void onStart() {
        super.onStart();
        setupRecyclerView();
        setupRequestButtonListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void setupRecyclerView() {
        ratesList.setAdapter(adapter);
        ratesList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupRequestButtonListener() {
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputCurrency.getText().toString().equals("")) {
                    subscription = restService.getCurrencyRate(inputCurrency.getText().toString())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Subscriber<CurrencyRate>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(CurrencyRate currencyRate) {
                                    adapter.clearList();
                                    adapter.addRates(currencyRate.getRates());
                                }
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Wrong input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
