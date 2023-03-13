package com.cj.bunnywallet.feature.importwallet.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R

@Composable
fun ImportFromSeedTitle() {
    Text(
        text = stringResource(id = R.string.import_from_seed),
        modifier = Modifier.padding(top = 24.dp),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}