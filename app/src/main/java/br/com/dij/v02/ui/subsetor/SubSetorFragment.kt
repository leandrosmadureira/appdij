package br.com.dij.v02.ui.subsetor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dij.v02.R
import br.com.dij.v02.databinding.FragmentSubSetorBinding
import br.com.dij.v02.ui.DBHelper.DBHelper
import br.com.dij.v02.ui.remote.subsetor.SubSetorAdapter
import br.com.dij.v02.ui.remote.subsetor.SubSetorResponse

class SubSetorFragment : Fragment() {
    private var _binding: FragmentSubSetorBinding? = null

    private val subSetorList: MutableList<SubSetorResponse> = mutableListOf()
    private lateinit var subSetorAdapter: SubSetorAdapter

    private fun getData(): MutableList<SubSetorResponse> {
        val dbHelper = DBHelper(requireContext())
        val allSSetor = dbHelper.getAllSunSetor()

        for (i in allSSetor){
            val subSetor = SubSetorResponse(i.cd_sub_setor,i.ds_sub_setor,null,i.tp_zona,i.sn_ativo)
            subSetorList.add(subSetor)
        }
        println(subSetorList)
        return subSetorList
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val subSetorViewModel =
            ViewModelProvider(this).get(SubSetorViewModel::class.java)

        _binding = FragmentSubSetorBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val dbHelper = DBHelper(requireContext())
        val allSSetor = dbHelper.getAllSunSetor()

        val textView: TextView = binding.textHome

        subSetorViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.listSubSetor)
        val subSetorAdapter = SubSetorAdapter(this.context,getData())

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = subSetorAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}