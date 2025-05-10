package br.com.dij.v02.ui.remote.leitura

class Leitura(
    val ds_lote: String,
    val nm_produtor: String,
    val ds_hidrometro: String,
    val cd_sub_setor:Int,
    val dt_leitura_anterior: String,
    val mn_leitura_anterior: Int,
    val mp_leitura_anterior: Int,
    val mn_leitura: Float,
    val mp_leitura: Float,
    val dt_leitura: String,
    val nr_classificacao: String,
    val sn_ativo: String
)