package br.senai.sp.informatica.mobileb.listademusicas.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.LinkedList;
import java.util.List;

import br.senai.sp.informatica.mobileb.listademusicas.Main;

public class MusicaDAO {

    public static MusicaDAO manager = new MusicaDAO();
    private MusicaDbHelper dbo;

    private MusicaDAO(){
        dbo = new MusicaDbHelper(Main.getContext());
    }

    public void salvar(Musica obj){
       SQLiteDatabase db = dbo.getWritableDatabase();

        if(obj.getId() == null) {
            String inserir = "insert into " + MusicaDbHelper.TABELA + "(titulo, artista, album, data, capa) values(?, ?, ?, ?, ?)";
            db.execSQL(inserir, new Object[]{obj.getTitulo(), obj.getArtista(), obj.getAlbum(), obj.getDtInclusao(), obj.getCapa()!= null ? obj.getCapa():new byte[]{}});
        } else {
            String update = "update " + MusicaDbHelper.TABELA + " set titulo = ?, artista = ?, album = ?, data = ?, capa = ? where _id = ?";
            db.execSQL(update, new Object[]{obj.getTitulo(), obj.getArtista(), obj.getAlbum(), obj.getDtInclusao(), obj.getCapa()!= null ? obj.getCapa():new byte[]{}, obj.getId()});
        }
        db.close();
    }

    public List<Musica> getLista(){
        List<Musica> musicas = new LinkedList<>();
        String rawQuery = "select _id, titulo, artista, album, data, capa from " +
                MusicaDbHelper.TABELA + " order by titulo";
        SQLiteDatabase db = dbo.getReadableDatabase();
        Cursor cursor  = db.rawQuery(rawQuery, null);

        if(cursor.moveToFirst()){
            do{
                Musica musica = new Musica();
                musica.setId(cursor.getLong(0));
                musica.setTitulo(cursor.getString(1));
                musica.setArtista(cursor.getString(2));
                musica.setAlbum(cursor.getString(3));
                musica.setDtInclusao(cursor.getString(4));
                musica.setCapa(cursor.getBlob(5));
                musicas.add(musica);
            }while (cursor.moveToNext());
        }
        return musicas;
    }

    //localizar
    public Musica getMusica(final Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "select _id, titulo, artista, album, data, capa from " + MusicaDbHelper.TABELA + " where _id = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Musica music = new Musica();
        music.setId(cursor.getLong(0));
        music.setTitulo(cursor.getString(1));
        music.setArtista(cursor.getString(2));
        music.setAlbum(cursor.getString(3));
        music.setDtInclusao(cursor.getString(4));
        music.setCapa(cursor.getBlob(5));
        db.close();
        return music;
    }

    public void remover(Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();

        String deletar = "delete from " + MusicaDbHelper.TABELA + " where _id = ?";
        db.execSQL(deletar, new Object[]{id});
        db.close();
    }
}
