package br.com.dij.v02.ui.remote.subsetor

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.dij.v02.databinding.FragmentSincronizarBinding
import br.com.dij.v02.ui.DBHelper.DBHelper
import br.com.dij.v02.ui.http.HttpHelper

class SincronizarFragment : Fragment() {
    private var _binding: FragmentSincronizarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sincronizarViewModel =
            ViewModelProvider(this).get(SincronizarViewModel::class.java)

        _binding = FragmentSincronizarBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.txtCdSubSetor

        binding.btnSincronizar.setOnClickListener {
            val dbHelper = DBHelper(requireContext())
            val server = HttpHelper()
            val config = dbHelper.getConffigId("url")
            dbHelper.deleteAllSubSetor()
            sincronizarViewModel.fetchSubSetores(server, config.ds_valor, binding.txtCdSubSetor.toString())
            Toast.makeText(requireContext(), config.ds_valor, Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), "SubSetores Importados!", Toast.LENGTH_SHORT).show()

            sincronizarViewModel.subsetorList.observe(viewLifecycleOwner) { subsetorList ->
                val dbHelper = DBHelper(requireContext())
                // Atualize a UI com a lista de subsetores
                for( item in subsetorList){
                    dbHelper.insertSubSetor(item);
                }
                Toast.makeText(requireContext(), "SubSetores atualizados!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnZeraBase.setOnClickListener {
            val dbHelper = DBHelper(requireContext())
            dbHelper.deleteAllLeitura()
            dbHelper.deleteAllSubSetor()

            Toast.makeText(requireContext(), "Base zerada!", Toast.LENGTH_SHORT).show()
        }

        sincronizarViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}