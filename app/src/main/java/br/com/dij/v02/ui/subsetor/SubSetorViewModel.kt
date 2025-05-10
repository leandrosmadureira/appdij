package br.com.dij.v02.ui.subsetor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import br.com.dij.v02.databinding.FragmentSubSetorBinding
import br.com.dij.v02.ui.remote.subsetor.SubSetorAdapter
import br.com.dij.v02.ui.remote.subsetor.SubSetorResponse

class SubSetorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SubSetor Fragment"
    }
    val text: LiveData<String> = _text
}