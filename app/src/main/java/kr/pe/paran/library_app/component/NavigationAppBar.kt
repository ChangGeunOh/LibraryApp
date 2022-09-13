package kr.pe.paran.library_app.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavigationAppBar(
    title: String,
    onClickBack: () -> Unit,
    actions: @Composable() (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "BackHome"
                )
            }
        },
        actions = { actions() }
    )
}