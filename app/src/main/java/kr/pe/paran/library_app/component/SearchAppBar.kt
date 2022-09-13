package kr.pe.paran.library_app.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    onClickBack: () -> Unit,
    onClickSearch: (String) -> Unit,
    onChangeValue: (String) -> Unit,
    onClickClose: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            GenieSearchView(
                holderText = "Search here...",
                onTextChange = { onChangeValue(it) },
                onSearchClicked = { onClickSearch(it) },
                onCloseClicked = { onClickClose() },
                query = ""
            )
        },
    )
}