package com.example.miproyecto.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miproyecto.data.model.Product

class ProductAdapter(
    private val list: List<Product>,
    private val onClick: (Product) -> Unit
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]

        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = "${product.price} €"
        holder.binding.tvCategory.text = product.category

        holder.itemView.setOnClickListener {
            onClick(product)
        }
    }

    override fun getItemCount(): Int = list.size
}