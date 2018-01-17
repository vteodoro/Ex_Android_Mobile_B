package br.senai.sp.informatica.mobileb.listademusicas.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MusicaDAO {

    private MusicaDbHelper dbo;

    public MusicaDAO (Context context){
        dbo = new MusicaDbHelper(context);
    }

    public void salvar(Musica obj){

       SQLiteDatabase db = dbo.getWritableDatabase();

        String inserir = "insert into " + MusicaDbHelper.TABELA + "(titulo, artista, album, data) values(?, ?, ?, ?)";
        db.execSQL(inserir, new Object[]{obj.getTitulo(), obj.getArtista(), obj.getAlbum(), obj.getDtInclusao()});
        db.close();
    }

    public List<Musica> getLista(){
        List<Musica> musicas = new LinkedList<>();
        String rawQuery = "select _id, titulo, artista, album, data from " +
                MusicaDbHelper.TABELA;
        SQLiteDatabase db = dbo.getReadableDatabase();
        Cursor cursor  = db.rawQuery(rawQuery, null);
        Musica musica = null;

        if(cursor.moveToFirst()){
            do{
                musica.setId(cursor.getLong(0));
                musica.setTitulo(cursor.getString(1));
                musica.setArtista(cursor.getString(2));
                musica.setAlbum(cursor.getString(3));
                musica.setDtInclusao(cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return musicas;
    }

    public void atualizar(Musica obj){
        SQLiteDatabase db = dbo.getWritableDatabase();
        String update = "update " + MusicaDbHelper.TABELA + " set titulo = ?, artista = ?, album = ?, data = ? where _id = ?";
        db.execSQL(update, new Object[]{obj.getTitulo(), obj.getArtista(), obj.getAlbum(), obj.getDtInclusao(), obj.getId()});
        Log.d("sql: ", update);
        db.close();

    }

    //localizar
    public Musica getMusica(final Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "select _id, titulo, artista, album, data from " + MusicaDbHelper.TABELA + "where _id = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Musica music = new Musica();
        music.setId(cursor.getLong(0));
        music.setTitulo(cursor.getString(1));
        music.setArtista(cursor.getString(2));
        music.setAlbum(cursor.getString(3));
        music.setDtInclusao(cursor.getString(4));
        db.close();
        return music;
    }

    public void remover(Musica obj) {
        SQLiteDatabase db = dbo.getWritableDatabase();

        String deletar = "delete from " + MusicaDbHelper.TABELA + " where _id = ?";
        db.execSQL(deletar, new Object[]{obj.getId()});
        db.close();
    }
}
