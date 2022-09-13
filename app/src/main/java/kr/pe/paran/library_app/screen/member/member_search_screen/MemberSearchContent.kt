package kr.pe.paran.library_app.screen.member.member_search_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.model.MemberData

@Composable
fun MemberSearchContent(
    memberList: List<MemberData>,
    onClickItem: (MemberData) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items = memberList) {
            MemberManagerItem(it, onClickItem = { memberData ->  onClickItem(memberData) })
        }
    }
}

@Composable
fun MemberManagerItem(memberData: MemberData, onClickItem: (MemberData) -> Unit) {

    Column(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .fillMaxWidth()
        .clickable { onClickItem(memberData) }
    ) {
        Text(text = memberData.personData.name, fontSize = 14.sp)
        Text(text = memberData.personData.addressData.getSummary(), fontSize = 12.sp)
    }
}
