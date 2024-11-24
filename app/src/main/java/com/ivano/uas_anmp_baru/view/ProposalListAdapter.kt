package com.ivano.uas_anmp_baru.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_anmp_baru.databinding.FragmentProposalTeamBinding
import com.ivano.uas_anmp_baru.databinding.ProposalItemLayoutBinding
import com.ivano.uas_anmp_baru.model.AppliedTeam

class ProposalListAdapter(val AppliedTeamList:ArrayList<AppliedTeam>):RecyclerView.Adapter<ProposalListAdapter.AppliedTeamViewHolder>() {
    class AppliedTeamViewHolder(val binding: ProposalItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppliedTeamViewHolder {
        var binding = ProposalItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppliedTeamViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return AppliedTeamList.size
    }

    override fun onBindViewHolder(holder: AppliedTeamViewHolder, position: Int) {
        holder.binding.proposal = AppliedTeamList[position]
//        holder.binding.txtNamaGame.text = AppliedTeamList[position].game.toString()
//        holder.binding.txtStatusProposal.text = "Waiting"
    }

    fun updateProposalList(newTodoList: List<AppliedTeam>) {
        AppliedTeamList.clear()
        AppliedTeamList.addAll(newTodoList)
        notifyDataSetChanged()
    }
    fun onCreateProposal(v: View) {
        val action = ProposalTeamFragmentDirections.actionApplyTeam()
        Navigation.findNavController(v).navigate(action)
    }

}