package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.customview.VerticalGrid
import com.cj.bunnywallet.MNEMONIC_SIZE_12
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot

@Composable
fun SRPBox(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 340.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
private fun SRPContent(
    mnemonic: List<PhraseSlot>,
    content: @Composable RowScope.(PhraseSlot) -> Unit,
) {
    VerticalGrid(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(size = 10.dp),
            )
            .padding(all = 8.dp),
        columns = 2,
    ) {
        mnemonic.forEachIndexed { i, ps ->
            Row(
                modifier = Modifier.padding(all = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${i + 1}.",
                    modifier = Modifier
                        .weight(weight = 1f)
                        .padding(end = 4.dp),
                    textAlign = TextAlign.Center,
                )
                content(ps)
            }
        }
    }
}

@Composable
private fun RowScope.PhraseView(phrase: String, modifier: Modifier) {
    Text(
        text = phrase,
        modifier = Modifier
            .weight(weight = 4f)
            .then(modifier)
            .padding(vertical = 12.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun ConfirmSRPContent(mnemonic: List<PhraseSlot>) {
    SRPContent(mnemonic = mnemonic) { ps ->
        val border = when {
            ps.phrase.isNotBlank() -> srpSolidBorder
            ps.selected -> srpDashedBorder
            else -> srpGrayDashedBorder
        }

        PhraseView(phrase = ps.phrase, modifier = border)
    }
}

@Composable
fun RevealSRPContent(mnemonic: List<String>) {
    val phraseSlots = mnemonic.mapIndexed { index, phrase ->
        PhraseSlot(pos = index, phrase = phrase)
    }
    SRPContent(mnemonic = phraseSlots) { ps ->
        PhraseView(phrase = ps.phrase, modifier = srpSolidBorder)
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewSRPBoxReveal() {
    SRPBox {
        ConfirmSRPContent(
            mnemonic = List(size = MNEMONIC_SIZE_12) {
                when (it) {
                    0 -> PhraseSlot(pos = it, phrase = "bunny")
                    1 -> PhraseSlot(pos = it, selected = true)
                    else -> PhraseSlot(pos = it)
                }
            },
        )
    }
}
