package app.hayarasikhah.portalti2016.Entity;

import java.io.Serializable;

/**
 * Created by Haya Rasikhah on 12/9/2018.
 */

public class Mahasiswa implements Serializable {
    private int id;
    private String name;
    private String nim;

    public Mahasiswa (String name, String nim) {
        this.name = name;
        this.nim = nim;

    }

    public String getName() {
        return name;
    }
     public String getNim() {
         return nim;
     }

    public int getId() {
        return id;
    }
}
