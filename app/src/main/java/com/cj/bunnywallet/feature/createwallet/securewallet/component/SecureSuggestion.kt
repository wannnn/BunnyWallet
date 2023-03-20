package com.cj.bunnywallet.feature.createwallet.securewallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R

@Composable
fun SecureSuggestions() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Suggestion(
            R.drawable.ic_edit_document,
            R.string.write_down_title,
            R.string.write_down_content,
        )
        Suggestion(
            R.drawable.ic_cloud_done,
            R.string.store_digitally_title,
            R.string.store_digitally_content,
        )
        Suggestion(
            R.drawable.ic_dynamic_feed,
            R.string.split_title,
            R.string.split_content,
        )
        Suggestion(
            R.drawable.ic_cognition,
            R.string.memorize_title,
            R.string.memorize_content,
        )
    }
}

@Composable
private fun Suggestion(icon: Int, title: Int, content: Int) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(id = title),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = stringResource(id = content),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSecureSuggestions() {
    SecureSuggestions()
}

@Preview(showBackground = true)
@Composable
fun PreviewSuggestion() {
    Suggestion(
        R.drawable.ic_edit_document,
        R.string.write_down_title,
        R.string.write_down_content,
    )
}