package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
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
import com.cj.bunnywallet.feature.common.VerticalGrid
import com.cj.bunnywallet.ui.theme.PurpleGrey80

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
fun SRPContent(mnemonic: List<String>, modifier: Modifier) {
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
        mnemonic.forEachIndexed { i, phrase ->
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
                Text(
                    text = phrase,
                    modifier = Modifier
                        .weight(weight = 4f)
                        .then(modifier)
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSRPBoxReveal() {
    SRPBox {
        SRPContent(
            mnemonic = listOf(
                "summer", "summer", "summer", "summer",
                "whale", "whale", "whale", "whale",
                "thank", "thank", "thank", "thank",
            ),
            modifier = srpSolidBorder,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSRPBoxConfirm() {
    SRPBox {
        SRPContent(
            mnemonic = listOf("", "", "", "", "", "", "", "", "", "", "", ""),
            modifier = srpDashedBorder,
        )
    }
}