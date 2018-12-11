package app.hayarasikhah.portalti2016.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.hayarasikhah.portalti2016.R;

/**
 * Created by Haya Rasikhah on 12/9/2018.
 */

public class MahasiswaHolder extends RecyclerView.ViewHolder {

    public TextView txtnama, txtnim, btnDelete;

    public MahasiswaHolder(View itemView){

        super(itemView);

        txtnama = (TextView) itemView.findViewById(R.id.txt_nama);
        txtnim = (TextView) itemView.findViewById(R.id.txt_nim);
        btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
    }
}
