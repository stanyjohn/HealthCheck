package com.sample.splashsample.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.splashsample.databinding.AdapterHealthBinding
import com.sample.splashsample.modal.Health


class HealthAdapter : RecyclerView.Adapter<HealthViewHolder>() {
    private var healthList = mutableListOf<Health>()

    fun setHealthDataList(health: List<Health>) {
        this.healthList.addAll(health.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterHealthBinding.inflate(inflater, parent, false)
        return HealthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val data = healthList[position]
        val context = holder.itemView.context
        holder.binding.heading.text = data.name
        val layout = holder.binding.linearLayout

        data.accessible.forEach {
            println(it.type)
            val childLayout = LinearLayout(context)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            childLayout.layoutParams = params

            val keytextView = TextView(context)
            keytextView.layoutParams = TableLayout.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f
            )
            keytextView.setTextColor(Color.BLACK)
            if (it.type != null){
                keytextView.text = it.type
            } else{
                keytextView.text = it.name
            }
            keytextView.setPadding(0, 5, 0, 0)
            keytextView.gravity = Gravity.START

            val valuetextView = TextView(context)
            valuetextView.layoutParams = TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 4f
            )
            valuetextView.setTextColor(Color.BLACK)
            valuetextView.text = it.success.toString()
            valuetextView.gravity = Gravity.END
            valuetextView.setPadding(0, 5, 0, 0)

            childLayout.addView(valuetextView, 0)
            childLayout.addView(keytextView, 0)

            layout.addView(childLayout)
        }

    }

    override fun getItemCount(): Int {
        return healthList.size
    }
}

class HealthViewHolder(val binding: AdapterHealthBinding) : RecyclerView.ViewHolder(binding.root){

}
