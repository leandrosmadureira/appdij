package br.com.dij.v02.ui.leitura

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.dij.v02.databinding.FragmentLeituraBinding
import br.com.dij.v02.ui.DBHelper.DBHelper
import br.com.dij.v02.ui.remote.leitura.Leitura

class LeituraFragment : Fragment() {

    private var _binding: FragmentLeituraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val leituraViewModel =
            ViewModelProvider(this).get(LeituraViewModel::class.java)

        _binding = FragmentLeituraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtLeituraPrincipal
        leituraViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.btnLeitura.setOnClickListener {
            val ds_lote = binding.txtLote.text.toString()
            val ds_hidrometro = binding.txtDsHidrometro.text.toString()
            val nm_leitura = binding.txtLeituraPrincipal.text.toString().toFloat()
            val mp_leitura = binding.txtLeituraNoturna.text.toString().toFloat()
            val leitura = Leitura(ds_lote,"Produtor", ds_hidrometro,1,"12/12/2025",12121,2321, nm_leitura, mp_leitura,"01/01/2025","123","S")
            val dbHelper = DBHelper(requireContext())
            dbHelper.insertLeitura(leitura)
            Toast.makeText(requireContext(), "Leitura salva com sucesso!", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}