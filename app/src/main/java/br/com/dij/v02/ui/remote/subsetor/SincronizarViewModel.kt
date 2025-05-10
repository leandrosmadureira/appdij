package br.com.dij.v02.ui.remote.subsetor

import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dij.v02.ui.http.HttpHelper
import kotlinx.coroutines.launch

class SincronizarViewModel : ViewModel() {
    private val _subsetorList = MutableLiveData<List<SubSetorResponse>>()
    val subsetorList: LiveData<List<SubSetorResponse>> = _subsetorList

    fun fetchSubSetores(server: HttpHelper, configUrl: String, cd_setor: String?) {
        viewModelScope.launch {
            server.getSubSetor(configUrl,cd_setor).collect { subsetorListServer ->
                _subsetorList.value = subsetorListServer
            }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }

    val text: LiveData<String> = _text
}