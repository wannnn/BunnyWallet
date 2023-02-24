package com.cj.bunnywallet.feature.importwallet.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.cj.bunnywallet.feature.common.CommonTextField

@Composable
fun PhraseTextField(
    number: String,
    phraseState: String,
    phraseStateUpdate: (String) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = number,
            modifier = Modifier.weight(weight = 2f),
            textAlign = TextAlign.Center
        )
        CommonTextField(
            valueState = phraseState,
            onValueUpdate = phraseStateUpdate,
            modifier = Modifier.weight(weight = 8f)
        )
    }
}