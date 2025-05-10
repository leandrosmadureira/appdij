package br.com.dij.v02.ui.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.dij.v02.ui.config.ConfigResponse
import br.com.dij.v02.ui.remote.leitura.Leitura
import br.com.dij.v02.ui.remote.subsetor.SubSetorResponse


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private val DATABASE_NAME = "dij.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE if not exists leitura (ds_lote TEXT PRIMARY KEY,nm_produtor TEXT,ds_hidrometro TEXT,dt_leitura_anterior TEXT,mp_leitura_anterior REAL,mn_leitura_anterior REAL,nr_classificacao INT,cd_sub_setor INT,dt_leitura INT,mp_leitura REAL,mn_leitura REAL)"
        db?.execSQL(createTableQuery)
        val createTableSSQuery = "CREATE TABLE if not exists sub_setor (cd_sub_setor TEXT PRIMARY KEY,ds_sub_setor TEXT,tp_zona TEXT,cd_setor INT,sn_ativo TEXT)"
        db?.execSQL(createTableSSQuery)
        val createTableHQuery = "CREATE TABLE if not exists hidrometro (ds_hidrometro TEXT PRIMARY KEY,ds_lote TEXT,mp_capacidade_maxima REAL,mn_capacidade_maxima REAL)"
        db?.execSQL(createTableHQuery)
        val createTableCQuery = "CREATE TABLE if not exists config (ds_chave TEXT PRIMARY KEY,ds_valor TEXT)"
        db?.execSQL(createTableCQuery)
        val insTableCQuery = "insert into config (ds_chave,ds_valor) values ('url','http://10.0.2.2/dij_api')"
        db?.execSQL(insTableCQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS leitura"
        db?.execSQL(dropTableQuery)
        val createTableSSQuery = "DROP TABLE IF EXISTS sub_setor"
        db?.execSQL(createTableSSQuery)
        val createTableHQuery = "DROP TABLE IF EXISTS hidrometro"
        db?.execSQL(createTableHQuery)
        val createTableCQuery = "DROP TABLE IF EXISTS config"
        db?.execSQL(createTableCQuery)
        onCreate(db)
    }

    fun insertLeitura(leitura: Leitura){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ds_lote", leitura.ds_lote)
            put("nm_produtor", leitura.nm_produtor)
            put("dt_leitura_anterior", leitura.dt_leitura_anterior)
            put("mn_leitura_anterior", leitura.mn_leitura_anterior)
            put("mp_leitura_anterior", leitura.mp_leitura_anterior)
            put("ds_hidrometro", leitura.ds_hidrometro)
            put("nr_classificacao", leitura.nr_classificacao)
            put("cd_sub_setor", leitura.cd_sub_setor)
        }
        db.insert("leitura", null, values)
        db.close()
    }

    fun updateLeitura(id: Int, valor: String){
        val db = writableDatabase
        val updateQuery = "UPDATE leitura SET valor = ? WHERE id = ?"
        db.execSQL(updateQuery, arrayOf(valor, id))
        db.close()
    }

    fun deleteLeitura(id: Int){
        val db = writableDatabase
        val deleteQuery = "DELETE FROM leitura WHERE id = ?"
        db.execSQL(deleteQuery, arrayOf(id))
        db.close()
    }

    fun deleteAllLeitura(){
        val db = writableDatabase
        db.delete("leitura", null, null)
        db.close()
    }

    fun insertConfig(config: ConfigResponse){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ds_chave", config.ds_chave)
            put("ds_valor", config.ds_valor)
        }
        db.insert("sub_setor", null, values)
        db.close()
    }
    fun updateConfig(ds_chave: String, ds_valor: String){
        val db = writableDatabase
        val updateQuery = "UPDATE config SET ds_valor = ? WHERE ds_chave = ?"
        db.execSQL(updateQuery, arrayOf(ds_valor, ds_chave))
        db.close()
    }

    fun getConffigId(ds_chave: String): ConfigResponse {
        val db = readableDatabase
        val query = "SELECT * FROM config WHERE ds_chave = ?"
        val cursor = db.rawQuery(query, arrayOf(ds_chave))
        cursor.moveToNext()
        val ds_chave = cursor.getString(cursor.getColumnIndexOrThrow("ds_chave"))
        val ds_valor = cursor.getString(cursor.getColumnIndexOrThrow("ds_valor"))
        cursor.close()
        db.close()
        return ConfigResponse(ds_chave, ds_valor)
    }

    fun insertSubSetor(subsetor: SubSetorResponse){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("cd_sub_setor", subsetor.cd_sub_setor)
            put("ds_sub_setor", subsetor.ds_sub_setor)
            put("cd_setor", subsetor.cd_setor)
            put("tp_zona", subsetor.tp_zona)
            put("sn_ativo", subsetor.sn_ativo)
        }
        db.insert("sub_setor", null, values)
        db.close()
    }

    fun getAllSunSetor(): List<SubSetorResponse>{
        val subSetorsList = mutableListOf<SubSetorResponse>()
        val db = readableDatabase
        val query = "SELECT * FROM sub_setor"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()){
            val cd_sub_setor = cursor.getString(cursor.getColumnIndexOrThrow("cd_sub_setor")).toInt()
            val ds_sub_setor = cursor.getString(cursor.getColumnIndexOrThrow("ds_sub_setor"))
            val cd_setor = cursor.getString(cursor.getColumnIndexOrThrow("cd_setor")).toInt()
            val tp_zona = cursor.getString(cursor.getColumnIndexOrThrow("tp_zona"))
            val sn_ativo = cursor.getString(cursor.getColumnIndexOrThrow("sn_ativo"))
            val subSetor = SubSetorResponse(cd_sub_setor, ds_sub_setor, cd_setor, tp_zona, sn_ativo)
            subSetorsList.add(subSetor)
        }
        cursor.close()
        db.close()
        return subSetorsList
    }

    fun deleteAllSubSetor(){
        val db = writableDatabase
        db.delete("sub_setor", null, null)
        db.close()
    }
}