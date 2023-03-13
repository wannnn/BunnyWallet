package com.cj.bunnywallet.feature.importwallet.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.common.CommonTextField

@Composable
fun PhraseTextField(
    number: String,
    phraseState: String,
    phraseStateUpdate: (String) -> Unit,
    showPhrase: Boolean
) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = number,
            modifier = Modifier.weight(weight = 1f),
            textAlign = TextAlign.Center
        )
        CommonTextField(
            valueState = phraseState,
            onValueUpdate = phraseStateUpdate,
            modifier = Modifier.weight(weight = 4f),
            visualTransformation = if (showPhrase) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
    }
}