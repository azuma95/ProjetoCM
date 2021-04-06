package ipvc.estg.aplicacaocm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.aplicacaocm.NotaEspecificaListener
import ipvc.estg.aplicacaocm.R
import ipvc.estg.aplicacaocm.entities.Nota

class NotaListAdapter internal constructor(context: Context, private val notaEspecificaListener: NotaEspecificaListener) : RecyclerView.Adapter<NotaListAdapter.NotaViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Nota>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NotaViewHolder(itemView)
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notaItemViewTitle: TextView = itemView.findViewById(R.id.textViewTitle);
        val notaItemViewContent: TextView = itemView.findViewById(R.id.textViewContent);

    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = notas[position]

        holder.notaItemViewTitle.text = current.titulo
        holder.notaItemViewContent.text = current.descricao

        holder.itemView.setOnClickListener {
            notaEspecificaListener.onNotaEspecificaListener(current)
        }

    }
    internal fun setNotas(notas: List<Nota>) {
        this.notas = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notas.size
}