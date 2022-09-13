package kr.pe.paran.library_app.screen.book_item.book_item_manager_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.component.GenieBarCodeView
import kr.pe.paran.library_app.component.GenieDropDownMenu
import kr.pe.paran.library_app.component.GenieOutlinedTextField
import kr.pe.paran.library_app.component.GenieRoundedButton
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.model.type.BookStatus
import kr.pe.paran.library_app.utils.Utils

@Composable
fun BookItemManagerContent(
    bookItemData: BookItemData,
    onClickScanner: () -> Unit,
    onChangeBookItem: (BookItemData) -> Unit,
    onClickUpdate: (BookItemData) -> Unit
) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        item {
            GenieOutlinedTextField(
                label = "구입도서",
                onValueChange = {},
                text = bookItemData.bookData.title,
                trailingIcon = {
                    IconButton(
                        onClick = { onClickScanner() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "Scanner Icon"
                        )
                    }
                }
            )

            GenieOutlinedTextField(
                label = "도서관",
                onValueChange = {
                    onChangeBookItem(bookItemData.copy(libraryName = it))
                }, text = bookItemData.libraryName
            )

            GenieOutlinedTextField(
                label = "구매일자",
                text = bookItemData.getPurchaseDate(),
            )

            GenieOutlinedTextField(
                label = "보관위치",
                onValueChange = {
                    onChangeBookItem(bookItemData.copy(rackLocationIdentifier = it))
                },
                text = bookItemData.rackLocationIdentifier
            )

            GenieDropDownMenu(
                items = bookItemData.bookItemStatusList(),
                text = bookItemData.status.name,
                onValueChange = {
                    onChangeBookItem(bookItemData.copy(status = BookStatus.valueOf(it)))
                }
            )

            GenieBarCodeView(barCode = bookItemData.barCode, isShowShareIcon = true)
//            Text(
//                text = "바코드",
//                modifier = Modifier.padding(top = 2.dp, start = 8.dp),
//                fontSize = 12.sp
//            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(2.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    modifier = Modifier
//                        .weight(1f)
//                        .height(50.dp),
//                    bitmap = Utils.getBarCode(bookItemData.barCode)!!.asImageBitmap(),
//                    contentDescription = "BookItem BarCode",
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                IconButton(
//                    onClick = {
//                        Utils.shareBarCode(
//                            context = context,
//                            barCode = bookItemData.barCode,
//                            title = bookItemData.bookData.title
//                        )
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Share,
//                        contentDescription = "BarCode Share",
//                        tint = Color.Gray
//                    )
//                }
//            }

            Spacer(modifier = Modifier.height(16.dp))
            GenieRoundedButton(
                text = "수정하기",
                enabled = bookItemData.isNotEmpty()
            ) {
                onClickUpdate(bookItemData)
            }
        }
    }

}