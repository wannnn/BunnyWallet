package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.customview.VerticalGrid
import com.cj.bunnywallet.MNEMONIC_SIZE_12
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot

@Composable
fun SRPBox(
    borderColor: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 340.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 10.dp),
            ),
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
    VerticalGrid(columns = 2, modifier = Modifier.padding(all = 8.dp)) {
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
private fun RowScope.PhraseView(
    phrase: String,
    modifier: Modifier,
    onClick: (() -> Unit)? = null,
) {
    Text(
        text = phrase,
        modifier = modifier
            .weight(weight = 4f)
            .ifClickable(condition = onClick != null) {
                this
                    .clip(shape = RoundedCornerShape(32.dp))
                    .clickable { onClick?.invoke() }
            }
            .padding(vertical = 12.dp),
        textAlign = TextAlign.Center,
    )
}

private fun Modifier.ifClickable(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier = if (condition) then(modifier()) else this

@Composable
fun ConfirmSRPContent(
    mnemonic: List<PhraseSlot>,
    onPhraseSlotClick: (PhraseSlot) -> Unit,
) {
    SRPContent(mnemonic = mnemonic) { ps ->
        val border = when {
            ps.phrase.isNotBlank() -> srpSolidBorder
            ps.selected -> srpDashedBorder
            else -> srpGrayDashedBorder
        }

        PhraseView(
            phrase = ps.phrase,
            modifier = border,
            onClick = { onPhraseSlotClick(ps) },
        )
    }
}

@Composable
fun RevealSRPContent(mnemonic: List<String>) {
    val phraseSlots = mnemonic.mapIndexed { index, phrase ->
        PhraseSlot(pos = index, phrase = phrase)
    }
    SRPContent(mnemonic = phraseSlots) { ps ->
        PhraseView(
            phrase = ps.phrase,
            modifier = srpSolidBorder,
            onClick = null,
        )
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
            onPhraseSlotClick = {},
        )
    }
}
