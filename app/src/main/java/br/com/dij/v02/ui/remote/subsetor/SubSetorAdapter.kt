package br.com.dij.v02.ui.remote.subsetor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dij.v02.databinding.SubSetorItemBinding

/*
class SubSetorAdapter(private val subSetorList: ArrayList<SubSetorResponse>) : RecyclerView.Adapter<SubSetorAdapter.SubSetorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubSetorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sub_setor_item,parent,false)
        return SubSetorViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return subSetorList.size
    }

    override fun onBindViewHolder(holder: SubSetorViewHolder, position: Int) {
        val currentItem = subSetorList[position]
        holder.rvCdSubSetor.text = currentItem.cd_sub_setor.toString()
        holder.rvDsSubSetor.text = currentItem.ds_sub_setor
        holder.rvSnAtivo.text = currentItem.sn_ativo
        holder.rvTpZona.text = currentItem.tp_zona
    }
    class SubSetorViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val rvCdSubSetor:TextView = itemView.findViewById(R.id.txtCdSubSetor)
        val rvDsSubSetor:TextView = itemView.findViewById(R.id.txtDsSubSetor)
        val rvTpZona:TextView = itemView.findViewById(R.id.txtTpZona)
        val rvSnAtivo:TextView = itemView.findViewById(R.id.txtSnAtivo)
    }
}

class SubSetorAdapter(val subSetorList: List<SubSetorResponse>):RecyclerView.Adapter<SubSetorAdapter.MainViewHolder>(){
    inner class MainViewHolder(val itemBinding: SubSetorItemBinding)
        :RecyclerView.ViewHolder(itemBinding.root){
            fun bindItem(subSetor: SubSetorResponse){
                itemBinding.txtCdSubSetor.text = subSetor.cd_sub_setor.toString()
                itemBinding.txtDsSubSetor.text = subSetor.ds_sub_setor
                itemBinding.txtTpZona.text = subSetor.tp_zona
                itemBinding.txtSnAtivo.text = subSetor.sn_ativo
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(SubSetorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return subSetorList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val subSetor = subSetorList[position]
        holder.bindItem(subSetor)

    }
}
*/
/*
class SubSetorAdapter(
    private val context: Context?
    ,private val subSetorList: MutableList<SubSetorResponse>
):
    RecyclerView.Adapter<SubSetorAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemList = SubSetorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(itemList)
    }

    override fun getItemCount() = subSetorList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.txtCdSubSetor.text = subSetorList[position].cd_sub_setor.toString()
        holder.txtDsSubSetor.text = subSetorList[position].ds_sub_setor
        holder.txtTpZona.text = subSetorList[position].tp_zona
        holder.txtSnAtivo.text = subSetorList[position].sn_ativo
    }

    inner class MainViewHolder(binding: SubSetorItemBinding): RecyclerView.ViewHolder(binding.root) {
        val txtCdSubSetor = binding.txtCdSubSetor
        val txtDsSubSetor = binding.txtDsSubSetor
        val txtTpZona = binding.txtTpZona
        val txtSnAtivo = binding.txtSnAtivo
    }
}
*/

class SubSetorAdapter(
    private val context: Context?
    ,private val subSetorList: MutableList<SubSetorResponse>
):
    RecyclerView.Adapter<SubSetorAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemList = SubSetorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(itemList)
    }

    override fun getItemCount():Int{
        return subSetorList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val post = subSetorList[position]
        holder.bind(post)
    }

    inner class MainViewHolder(
        private val binding: SubSetorItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(subSetor: SubSetorResponse) {
            binding.txtCdSubSetor.text = subSetor.cd_sub_setor.toString()
            binding.txtDsSubSetor.text = subSetor.ds_sub_setor.toString()
            binding.txtTpZona.text = subSetor.tp_zona.toString()
            binding.txtSnAtivo.text = subSetor.sn_ativo.toString()
        }
    }
}