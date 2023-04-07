package com.cj.bunnywallet.feature.home.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.feature.home.type.TransactionType

@Composable
fun TransactionView(
    onClick: (TransactionType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TransactionType.values().forEach {
            Item(
                icon = it.icon,
                txt = it.txt,
                onItemClick = { onClick.invoke(it) }
            )
        }
    }
}

@Composable
private fun Item(@DrawableRes icon: Int, @StringRes txt: Int, onItemClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val border = Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            shape = CircleShape
        )
        CommonIconBtn(icon = icon, modifier = border, onClick = onItemClick)
        Text(
            text = stringResource(id = txt),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}