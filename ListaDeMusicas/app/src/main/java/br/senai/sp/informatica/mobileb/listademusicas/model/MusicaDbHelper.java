package br.senai.sp.informatica.mobileb.listademusicas.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicaDbHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "db.musicas.db";
    public static final String TABELA = "musicas";
    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String ARTISTA = "artista";
    public static final String ALBUM = "album";
    public static final String DATA = "data";
    public static final String CAPA = "capa";

   // public static final String DURACAO = "duracao";

    private static final int VERSAO = 2;

    public MusicaDbHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String criarBD = "create table " + TABELA + "(" + ID
                + " integer primary key autoincrement," + TITULO
                + " text," + ARTISTA + " text," + ALBUM
                + " text," + CAPA + " blob," + DATA + " long)";
        sqLiteDatabase.execSQL(criarBD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        int versao = sqLiteDatabase.getVersion();
        switch (versao) {
            case 1:
                sqLiteDatabase.execSQL("ALTER TABLE  " + TABELA + " ADD COLUMN " + CAPA + " blob ");
//            case 2:
//                sqLiteDatabase.execSQL("ALTER TABLE  " + TABELA + " ADD COLUMN " + DURACAO + " int ");
        }
    }
}
//PRAGMA table_info(table-name); -> retorna a estrutura da tabela
//android.database.sqlite.SQLiteDatabase.getVersion()