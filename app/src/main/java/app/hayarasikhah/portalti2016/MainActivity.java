package app.hayarasikhah.portalti2016;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import app.hayarasikhah.portalti2016.Adapter.MahasiswaAdapter;
import app.hayarasikhah.portalti2016.Entity.DaftarMahasiswa;
import app.hayarasikhah.portalti2016.Entity.Mahasiswa;
import app.hayarasikhah.portalti2016.Network.Network;
import app.hayarasikhah.portalti2016.Network.Routes;
import app.hayarasikhah.portalti2016.util.Consts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //deklarasikan recyclerviewnya
    private RecyclerView lstMahasiswa;

    FloatingActionButton btn_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting recyclerviewnya dari id lst_mahasiswa yang ada di activity_main
        lstMahasiswa = (RecyclerView) findViewById(R.id.lst_mahasiswa);

        //set layout manager untuk lstMahasiswa
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstMahasiswa.setLayoutManager(linearLayoutManager);

        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);

        //requestDaftarMahasiswa();

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, DetailMahasiswaActivity.class);
                addIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_ADD);
                startActivity(addIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestDaftarMahasiswa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menampilkan menu di activity
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                //ketika icon refresh di klik, maka panggil ...
                requestDaftarMahasiswa();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestDaftarMahasiswa() {
        //pertama, memanggil request() dari retrofit yang sudah dibuat
        final Routes services = Network.request().create(Routes.class);

        //kita melakukan request terhadap getMahasiswa()
        services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {
                //mengecek request yang dilakukan, berhasil/tidak
                if (response.isSuccessful()) {
                    //casting data yang didapatkan, menjadi DaftarMahasiswa
                    DaftarMahasiswa mahasiswas = response.body();

                    //get title
                    Log.d("bismillah", mahasiswas.getTitle());

                    //tampilkan daftar mahasiswa di recyclerview
                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswas.getData());

                    //untuk handle button delete di item mahasiswa
                    //fungsinya untuk menghapus data yang ada di API
                    adapter.setListener(new MahasiswaAdapter.MahasiswaListener() {
                        @Override
                        public void onDelete(int mhsId) {
                            String id = String.valueOf(mhsId); //konversi int to string
                            deleteMahasiswa(services, id);
                        }
                    });

                    lstMahasiswa.setAdapter(adapter);
                } else {
                    //ketika data tidak berhasil di load
                    onMahasiswaError();
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {
                //ketika data tidak berhasil di load
                onMahasiswaError();
            }
        });
    }

    private void onMahasiswaError() {
        Toast.makeText(
                MainActivity.this,
                "Gagal. Silahkan periksa koneksi internet anda.",
                Toast.LENGTH_LONG).show();
    }

    private void deleteMahasiswa(final Routes services, final String mhsId) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.app_name);
        alert.setMessage("are you sure?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                services.deleteMahasiswa(mhsId).enqueue(new Callback<Mahasiswa>() {
                    @Override
                    public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                        if (response.isSuccessful()) {
                            requestDaftarMahasiswa();
                        } else {
                            onMahasiswaError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mahasiswa> call, Throwable t) {
                        onMahasiswaError();
                    }
                });
            }
        });
        alert.show();
    }

}