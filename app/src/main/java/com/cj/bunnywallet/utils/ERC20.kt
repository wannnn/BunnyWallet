package com.cj.bunnywallet.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.utils.Numeric
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ERC20 @Inject constructor() {
    /**
     *  Compound address: 0x5d3a536E4D6DbD6114cc1Ead35777bAB948E3643
     *  DAI address: 0x6B175474E89094C44Da98b954EedeAC495271d0F
     */
    fun getERC20Balance(address: String, contractAddress: String) = flow {
        val function = Function(
            "balanceOf",
            listOf(Address(address)),
            listOf(object : TypeReference<Address>() {}),
        )
        val ethCallTransaction: Transaction = Transaction.createEthCallTransaction(
            address,
            contractAddress,
            FunctionEncoder.encode(function),
        )
        val response: EthCall =
            Web3jManager.web3j.ethCall(ethCallTransaction, DefaultBlockParameterName.LATEST).send()

        val balance = Numeric.toBigInt(response.value).toString()
        Timber.d(message = "ERC20 balance: $balance")

        emit(balance)
    }
        .flowOn(Dispatchers.IO)
        .catch { Timber.d(message = "Get ERC20 balance fail: ${it.message}") }
}