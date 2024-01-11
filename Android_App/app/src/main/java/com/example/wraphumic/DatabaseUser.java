package com.example.wraphumic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseUser extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "DataUser.db";
    private static final int DATABASE_VERSION = 1;

    private static final String Table_Name = "DataPengguna";
    private static final String Nama_Lengkap = "NamaLengkap";
    private static final String Nama_User = "NamaUser";
    private static final String Password_User = "PasswordUser";

    public DatabaseUser(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE "+ Table_Name + " (" + Nama_Lengkap + " TEXT, "
                + Nama_User + " TEXT, " + Password_User + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    void TambahData(String nama_lengkap, String nama_User, String password_User)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Nama_Lengkap, nama_lengkap);
        cv.put(Nama_User, nama_User);
        cv.put(Password_User, password_User);
        long hasil = db.insert(Table_Name, null, cv);
        if(hasil==-1)
        {
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show();
        }
    }

}
