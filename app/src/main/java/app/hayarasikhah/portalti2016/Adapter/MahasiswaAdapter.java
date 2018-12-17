package app.hayarasikhah.portalti2016.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.hayarasikhah.portalti2016.DetailMahasiswaActivity;
import app.hayarasikhah.portalti2016.Entity.Mahasiswa;
import app.hayarasikhah.portalti2016.Holder.MahasiswaHolder;
import app.hayarasikhah.portalti2016.R;
import app.hayarasikhah.portalti2016.util.Consts;

/**
 * Created by Haya Rasikhah on 12/9/2018.
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder>{

    private List<Mahasiswa> mahasiswas;
    private MahasiswaListener listener;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public void setListener(MahasiswaListener listener) {
        this.listener = listener;
    }

    @Override
    public MahasiswaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        final MahasiswaHolder holder = new MahasiswaHolder(view);

        final Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //definisikan position untuk get mahasiswa
                int adapterPosition = holder.getAdapterPosition();
                Mahasiswa mahasiswa = mahasiswas.get(adapterPosition);

                Intent detailIntent = new Intent(context, DetailMahasiswaActivity.class);
                detailIntent.putExtra("mahasiswa", mahasiswa);
                detailIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_EDIT);
                context.startActivity(detailIntent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MahasiswaHolder holder, final int position) {
        holder.txtnama.setText(mahasiswas.get(position).getName());
        holder.txtnim.setText(mahasiswas.get(position).getNim());

        //fungsi delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(mahasiswas.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public interface MahasiswaListener{
        void onDelete(int mhsId);
    }
}
