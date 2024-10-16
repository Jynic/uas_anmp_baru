package com.ivano.uas_anmp_baru.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_anmp_baru.databinding.MemberListItemBinding
import com.ivano.uas_anmp_baru.model.Member
import com.squareup.picasso.Picasso

class MemberListAdapter(val memberList: ArrayList<Member>): RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>() {
    class MemberViewHolder(var binding: MemberListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Member){
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = MemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.binding.txtName.text = memberList[position].name
        holder.binding.txtRole.text = "Role: " + memberList[position].role

        val url = memberList[position].avatar
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{ picasso, uri, exception ->
            exception.printStackTrace()
        }
        Picasso.get().load(url).into(holder.binding.imgAvatar)
    }

    fun updateMemberList(newMemberList: ArrayList<Member>) {
        memberList.clear()
        memberList.addAll(newMemberList)
        notifyDataSetChanged()
    }
}