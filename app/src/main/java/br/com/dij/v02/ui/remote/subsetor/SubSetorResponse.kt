package br.com.dij.v02.ui.remote.subsetor

import kotlinx.serialization.Serializable

@Serializable
data class SubSetorResponse(
    var cd_sub_setor: Int ? =null,
    var ds_sub_setor: String ? =null,
    var cd_setor: Int ? =null,
    var tp_zona: String ? =null,
    var sn_ativo: String ? =null
)