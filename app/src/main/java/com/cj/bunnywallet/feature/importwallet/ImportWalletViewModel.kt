package com.cj.bunnywallet.feature.importwallet

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.extensions.isPasswordValid
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import org.web3j.crypto.Bip44WalletUtils
import javax.inject.Inject

@HiltViewModel
class ImportWalletViewModel @Inject constructor() : ViewModel(),
    Reducer<ImportWalletState> by ReducerImp(ImportWalletState()) {

    private var phraseList: MutableList<String> =
        MutableList(size = PhraseAmountType.TWELVE_WORDS.amount) { "" }

    fun handleEvent(event: ImportWalletEvent) {
        when (event) {
            is ImportWalletEvent.SetPhraseAmountType -> {
                setState(curState.copy(phraseAmount = event.phraseAmount))
                phraseList = MutableList(size = event.phraseAmount.amount) { "" }
            }
            is ImportWalletEvent.SetPassword -> {
                setState(
                    curState.copy(
                        password = event.pwd,
                        passwordValid = event.pwd.isPasswordValid()
                    )
                )
            }
            is ImportWalletEvent.SetConfirmPassword -> {
                setState(
                    curState.copy(
                        confirmPassword = event.pwd,
                        confirmPasswordValid = event.pwd.isNotBlank() && event.pwd == curState.password
                    )
                )
            }
            is ImportWalletEvent.UpdatePhrase -> {
                phraseList = phraseList.apply {
                    this[event.index] = event.phrase
                }
            }
            is ImportWalletEvent.Import -> {
                if (curState.passwordValid && curState.confirmPasswordValid && "" !in phraseList) {
                    importWallet()
                }
            }
        }
    }

    private fun importWallet() {
        val mnemonic = phraseList.joinToString(separator = " ")

        val credentials = Bip44WalletUtils.loadBip44Credentials("", mnemonic)

        println(credentials.address)
    }
}