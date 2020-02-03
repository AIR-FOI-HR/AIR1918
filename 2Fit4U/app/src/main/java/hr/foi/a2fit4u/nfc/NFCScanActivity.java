package hr.foi.a2fit4u.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.core.managers.DBManager;
import com.example.core.util.Constants;

import butterknife.ButterKnife;
import hr.foi.a2fit4u.MainActivity;
import hr.foi.a2fit4u.R;

public class NFCScanActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, Constants.NFC_TECH_LIST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED))
        {
            Toast.makeText(getBaseContext(),"NFC device found!", Toast.LENGTH_LONG);

            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] extraID = tagFromIntent.getId();

            StringBuilder sb = new StringBuilder();
            for (byte b : extraID) {
                sb.append(String.format("%02X", b));
            }
            //textView.setText("NFC Tag\n" + ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));

            //Provjera da li je NFC povezan za korisnika
            if(!DBManager.getInstance().checkEntity(Constants.DATA_TYPE_NFC, String.valueOf(sb)))
            {
                Intent activityIntent = new Intent(NFCScanActivity.this, MainActivity.class);
                activityIntent.putExtra("NFC Tag",String.valueOf(sb));
                startActivity(activityIntent);
            }


        }
    }
}
