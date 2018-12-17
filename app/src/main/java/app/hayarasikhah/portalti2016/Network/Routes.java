package app.hayarasikhah.portalti2016.Network;

import app.hayarasikhah.portalti2016.Entity.DaftarMahasiswa;
import app.hayarasikhah.portalti2016.Entity.Mahasiswa;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Haya Rasikhah on 12/9/2018.
 */

public interface Routes {

    //didapat dari https://ti16-api.herokuapp.com/ (Network)
    @GET("dev/list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    //didapat dari https://ti16-api.herokuapp.com/ (Network)
    @POST("dev/insert_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("name") String name,
            @Field("nim") String nim
    );
    @DELETE ("mahasiswatest/{mhsId}")
    Call<Mahasiswa> deleteMahasiswa(
            @Path("mhsId") String mhsId
    );


    @PUT ("mahasiswatest/{mhsId}")
    @FormUrlEncoded
    Call<Mahasiswa> updateMahasiswa(
            @Path("mhsId") String mhsId,
            @Field("name") String name,
            @Field("nim") String nim
    );
}
