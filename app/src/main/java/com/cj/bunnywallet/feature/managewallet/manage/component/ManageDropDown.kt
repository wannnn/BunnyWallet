package com.cj.bunnywallet.feature.managewallet.manage.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.feature.managewallet.manage.type.ManageType

@Composable
fun BoxScope.ManageDropDown(menuItemClick: (ManageType) -> Unit) {
    var dmExpand by remember { mutableStateOf(false) }

    Box(modifier = Modifier.align(Alignment.CenterEnd)) {
        CommonIconBtn(
            icon = R.drawable.ic_wallet,
            modifier = Modifier
                .padding(end = 8.dp),
            onClick = { dmExpand = true }
        )

        DropdownMenu(
            expanded = dmExpand,
            onDismissRequest = { dmExpand = false },
            modifier = Modifier.align(Alignment.TopEnd),
            offset = DpOffset(x = 12.dp, y = 0.dp),
        ) {
            ManageType.values().forEach {
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = it.typeName)) },
                    onClick = {
                        menuItemClick(it)
                        dmExpand = false
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(id = it.icon), contentDescription = null)
                    },
                )
            }
        }
    }
}