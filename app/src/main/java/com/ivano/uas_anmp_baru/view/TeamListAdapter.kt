package com.ivano.uas_anmp_baru.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_anmp_baru.databinding.TeamListItemBinding
import com.ivano.uas_anmp_baru.model.Achievement
import com.ivano.uas_anmp_baru.model.Team

class TeamListAdapter(val teamList: ArrayList<Team>, val gameId: Int): RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {
    class TeamViewHolder(var binding: TeamListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team){
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamListAdapter.TeamViewHolder {
        val binding = TeamListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamListAdapter.TeamViewHolder, position: Int) {
        holder.binding.txtTeamItem.text = teamList[position].name

        holder.binding.txtTeamItem.setOnClickListener {
            val action = TeamsFragmentDirections.actionTeamDetail(teamList[position].id, gameId)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    fun updateTeamList(newTeamList: ArrayList<Team>) {
        teamList.clear()
        teamList.addAll(newTeamList)
        notifyDataSetChanged()
    }
}