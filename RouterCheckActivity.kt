package com.example.wifiaudit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wifiaudit.databinding.ActivityRouterCheckBinding

class RouterCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRouterCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRouterCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gatewayIp = GatewayHelper.getGatewayIp(this)

        if (gatewayIp != null) {
            binding.tvGatewayIp.text = "آدرس پنل روتر شما: $gatewayIp"
            binding.btnOpenPanel.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$gatewayIp"))
                startActivity(intent)
            }
        } else {
            binding.tvGatewayIp.text = "به شبکه Wi-Fi متصل نیستید"
            binding.btnOpenPanel.isEnabled = false
        }

        val items = RouterChecklist.defaultItems().toMutableList()
        binding.recyclerChecklist.layoutManager = LinearLayoutManager(this)
        binding.recyclerChecklist.adapter = ChecklistAdapter(items)
    }
}

class ChecklistAdapter(private val items: MutableList<ChecklistItem>) :
    RecyclerView.Adapter<ChecklistAdapter.VH>() {

    inner class VH(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val checkbox: android.widget.CheckBox = view.findViewById(R.id.checkbox)
        val title: android.widget.TextView = view.findViewById(R.id.tvTitle)
        val desc: android.widget.TextView = view.findViewById(R.id.tvDesc)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checklist, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.desc.text = item.description
        holder.checkbox.isChecked = item.isChecked
        holder.checkbox.setOnCheckedChangeListener { _, checked ->
            item.isChecked = checked
        }
    }

    override fun getItemCount() = items.size
    }
