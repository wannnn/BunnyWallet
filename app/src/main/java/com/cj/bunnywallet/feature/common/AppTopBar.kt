package com.cj.bunnywallet.feature.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R

@Composable
fun AppTopBar(
    onBackClicked: () -> Unit,
    @StringRes appbarTitle: Int = R.string.app_name,
    showBackBtn: Boolean = true,
    @DrawableRes tailIcon: Int? = null,
    onTailIconClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {

        if (showBackBtn) {
            CommonIconBtn(
                icon = R.drawable.ic_arrow_back,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterStart),
                onClick = onBackClicked
            )
        }

        Text(
            text = stringResource(id = appbarTitle),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge
        )

        if (tailIcon != null) {
            CommonIconBtn(
                icon = tailIcon,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterEnd),
                onClick = onTailIconClicked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBar() {
    AppTopBar(onBackClicked = {})
}