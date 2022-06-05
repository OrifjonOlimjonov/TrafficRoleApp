package uz.orifjon.trafficroleapp.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.database.Role
import uz.orifjon.trafficroleapp.database.RoleDatabase
import uz.orifjon.trafficroleapp.databinding.RecyclerviewItemBinding

class RecyclerViewAdapter(
    var context: Context,
    var list: List<Role>,
    var onDeleteClick: (Role, Int) -> Unit,
    var onEditClick: (Role, Int) -> Unit,
    var isLiked: (Role, Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerViewAdapter.VH>() {

    inner class VH(var binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(role: Role, position: Int) {
            val bitmap = BitmapFactory.decodeByteArray(role.bitmap, 0, role.bitmap.size)
            binding.imgSysmbol.setImageBitmap(bitmap)
            binding.tvNameSymbol.text = role.roleName
            if (role.isLiked == 1) {
                binding.isLike.setBackgroundResource(R.drawable.ic_heart_1)
            } else {
                binding.isLike.setBackgroundResource(R.drawable.ic_heart__1__1)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(role, position)
            }
            binding.btnEdit.setOnClickListener {
                onEditClick(role, position)
            }
            binding.isLike.setOnClickListener {
                isLiked(role,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}