package br.com.dij.v02.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.dij.v02.databinding.FragmentConfigBinding
import br.com.dij.v02.ui.DBHelper.DBHelper
import br.com.dij.v02.ui.remote.leitura.Leitura

class ConfigFragment : Fragment() {

    private var _binding: FragmentConfigBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ConfigViewModel::class.java)

        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dbHelper = DBHelper(requireContext())

        binding.btnConfig.setOnClickListener {
            val url = binding.txtUrl.text.toString()
            val config = ConfigResponse("url", url)
            val dbHelper = DBHelper(requireContext())
            dbHelper.updateConfig("url",url)
            Toast.makeText(requireContext(), "Configuração salva com sucesso!", Toast.LENGTH_SHORT).show()
        }

        val config = dbHelper.getConffigId("url")
        binding.txtUrl.setText(config.ds_valor)

        val textView: TextView = binding.txtUrl
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = config.ds_valor
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}