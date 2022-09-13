package kr.pe.paran.library_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import kr.pe.paran.library_app.R
import kr.pe.paran.library_app.model.AuthorData
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.screen.book.BookViewModel
import kr.pe.paran.library_app.utils.Logcat

@ExperimentalComposeUiApi
@Composable
fun GenieAuthorDialog(
    modifier: Modifier = Modifier
        .padding(vertical = 64.dp)
        .fillMaxSize(),
    onClickAuthor: (AuthorData) -> Unit,
    onClickAdd: () -> Unit,
    onDismissDialog: () -> Unit,
    viewModel: BookViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    var authorList by remember { mutableStateOf(emptyList<AuthorData>()) }

    if (networkStatus is NetworkStatus.SUCCESS) {
        Logcat.i("networkState>${networkStatus.toString()}")
        if ((networkStatus as NetworkStatus.SUCCESS).data is List<*>) {
            authorList = (networkStatus as NetworkStatus.SUCCESS).data as List<AuthorData>
        }
    }

    Logcat.i("authorList>${authorList.toString()}")

    Dialog(
        onDismissRequest = { onDismissDialog() },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = modifier
        ) {
            GenieSearchView(
                onCloseClicked = {
                    onDismissDialog()
                },
                onTextChange = {
                    Logcat.i("Search:$it")
                    viewModel.searchAuthor(it)
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
                    .background(Color.White)
            ) {
                items(items = authorList) { authorData ->
                    AuthorItemView(authorData = authorData, onClickItem = { onClickAuthor(it) })
                }
            }

            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = {
                        onClickAdd()
                    }
                ) {
                    Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "Add Author")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorItemView(
    authorData: AuthorData = AuthorData(
        name = "전지현",
        description = "1997년 16살에 전지현은 아는 모델 언니를 따라갔다가 패션잡지 '에꼴'의 표지모델로 발탁되어 연예계에 데뷔하였다. 이를 눈여겨본 당시의 대형기획사 싸이더스의 정훈탁 대표에 의해 1년간 연기 수업을 받으면서 여러 편의 광고에 등장하였다. 광고 속 전지현을 눈여겨 본 오종록 PD는 1998년 드라마 《내 마음을 뺏어봐》에 캐스팅 제의를 했고, 이를 통해 드라마 데뷔를 해 전지현이라는 예명으로 활동을 시작하였다. 이때 SBS 인기가요의 MC로도 발탁되었는데, 첫 방송부터 멘트 실수가 터지면서 당장 MC를 바꾸라는 쓴소리와 함께 스포츠서울에서는 MC 자격 미달이라는 기사까지 나왔었다. 하지만 시간이 지날수록 진행이 안정되어 처음 같은 불호 반응은 점차 사그라들었다."
    ),
    onClickItem: (AuthorData) -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickItem(authorData)
            }
            .drawBehind {
                val strokeWidth = 1f
                val y = size.height - strokeWidth
                drawLine(
                    color = Color.LightGray,
                    Offset(x = 62f, y = y),
                    Offset(x = size.width - 62f, y),
                    strokeWidth = strokeWidth
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_profile_01),
            contentDescription = "Avatar",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = authorData.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = authorData.description,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray
            )

        }
    }
}