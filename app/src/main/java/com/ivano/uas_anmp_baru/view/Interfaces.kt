package com.ivano.uas_anmp_baru.view

import android.view.View

interface ButtonClickListener{
    fun onButtonClick(v: View)
}

interface TextInputClickListener {
    fun onInputClick(v: View)
}

interface ButtonActionNavClickListener {
    fun onButtonActionNavClick(v: View)
}

interface CheckBoxClickListener {
    fun onCheckBoxClick(v: View, isChecked: Boolean)
}

interface TambahProposalTeam {
    fun onCreateProposal(v: View)
}

interface SpinnerListener {
    fun onSpinnerItemSelected(item: Any)
}

interface AgreementListener {
    fun onAgreementClick(v: View)
}