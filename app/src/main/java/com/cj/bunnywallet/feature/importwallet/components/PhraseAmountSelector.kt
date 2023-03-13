package com.cj.bunnywallet.feature.importwallet.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType

private const val Rotate180 = 180f
private const val Rotate360 = 360f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhraseAmountSelector(
    currentPhraseAmount: PhraseAmountType,
    onSelected: (PhraseAmountType) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val rotate by animateFloatAsState(targetValue = if (expanded) Rotate180 else Rotate360)

    Column(
        modifier = Modifier.padding(top = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            AmountView(
                providedRotate = { rotate },
                txtId = currentPhraseAmount.txtId
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .exposedDropdownSize()
                    .background(Color.White)
            ) {
                PhraseAmountType.values().forEach { type ->
                    val bgColor = if (currentPhraseAmount == type) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = type.txtId)) },
                        onClick = {
                            onSelected.invoke(type)
                            expanded = false
                        },
                        modifier = Modifier.background(color = bgColor)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExposedDropdownMenuBoxScope.AmountView(
    providedRotate: () -> Float,
    txtId: Int
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .menuAnchor()
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = txtId),
            modifier = Modifier.padding(start = 5.dp),
            fontSize = 15.sp,
        )

        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .rotate(providedRotate()),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPhraseAmountSelector() {
    val words = remember { mutableStateOf(PhraseAmountType.TWELVE_WORDS) }
    PhraseAmountSelector(
        currentPhraseAmount = words.value,
        onSelected = { words.value = it }
    )
}