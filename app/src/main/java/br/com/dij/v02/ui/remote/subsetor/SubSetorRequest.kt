package br.com.dij.v02.ui.remote.subsetor

import kotlinx.serialization.Serializable

@Serializable
data class SubSetorRequest(
    val cd_sub_setor: Int,
    val ds_sub_setor: String,
    val cd_setor: Int,
    val tp_zona: String,
    val sn_ativo: String,
)