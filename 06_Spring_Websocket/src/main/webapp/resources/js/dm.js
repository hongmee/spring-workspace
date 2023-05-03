/**
 * 
 */

function sendDmModal(){
    $.ajax({
        url : "/selectAllMemberId.do",
        success : function(list){
            $("[name=receiver]").empty();
            for(let i=0;i<list.length;i++){
                const option = $("<option>");
                option.val(list[i]);
                option.text(list[i]);
                $("[name=receiver]").append(option);
            }
            $("#sendDm-modal").css("display", "flex");
        }
    })
}

function closeModal(){
    $("#sendDm-modal").hide();
    $("textarea[name=dmContent]").val("");
}

function dmSend(){
    const receiver = $("[name=receiver]").val();
    const sender = $("#sender").val();
    const dmContent = $("[name=dmContent]").val();

    $.ajax({
        url : "/insertDm.do",
        type : "post",
        data : {receiver : receiver, sender : sender, dmContent : dmContent},
        success : function (data){
            if(data == "0"){
                alert("쪽지보내기 실패");
            }

            // 실시간으로 상대가 쪽지를 보내면 받은사람이 접속해있을때
            // 새로고침없이 읽지않은 쪽지수 변경
            // header.jsp의 ws
            const sendData = {type : "sendDm", receiver : receiver};
            ws.send(JSON.stringify(sendData));

            // 보내기 후 새로고침없이 보낸쪽지함 조회
            getSendDm();
            closeModal();
        }
    })
}

function getSendDm(){
    // 보낸쪽지함
    const sender = $("#sender").val();

    $.ajax({
        url : "/myDmList.do",
        data : {sender : sender},
        success : function(list){
            const tbody = $(".sendDmTbl>tbody");
            tbody.empty();
            for(let i=0;i<list.length;i++){
                const dm = list[i];
                const tr = $("<tr>");
                // 보낸사람, 내용, 시간, 읽음 여부
                const td1 = $("<td>");
                td1.text(dm.receiver);
                const td2 = $("<td>");
                td2.text(dm.dmContent);
                const td3 = $("<td>");
                td3.text(dm.dmDate);
                const td4 = $("<td>");
                if(dm.readCheck == 0){
                    // 읽지 않았을 때 css
                    tr.addClass("bold");
                    td4.text("읽지않음");
                }else{
                    td4.text("읽음");
                }
                tr.append(td1).append(td2).append(td3).append(td4);
                tbody.append(tr);
            }
            console.log(list);
        }
    })
}

function getReceiverDm(){
    // 받은쪽지함
    const receiver = $("#sender").val();

    $.ajax({
        url : "/myDmList.do",
        data : {receiver : receiver},
        success : function(list){
            const tbody = $(".receiveDmTbl>tbody");
            tbody.empty();
            for(let i=0;i<list.length;i++){
                const dm = list[i];
                const tr = $("<tr>");
                // 받은사람, 내용, 시간, 읽음 여부
                const td1 = $("<td>");
                td1.text(dm.sender);
                const td2 = $("<td>");
                td2.text(dm.dmContent);

                // 상세보기
                td2.attr("onclick", "dmDetail("+dm.dmNo+");");

                const td3 = $("<td>");
                td3.text(dm.dmDate);
                const td4 = $("<td>");
                if(dm.readCheck == 0){
                    // 읽지 않았을 때 css
                    tr.addClass("bold");
                    td4.text("읽지않음");
                }else{
                    td4.text("읽음");
                }
                tr.append(td1).append(td2).append(td3).append(td4);
                tbody.append(tr);
            }
            console.log(list);
        }
    })
}

function dmDetail(dmNo){
    console.log(dmNo);

    $.ajax({
        url : "/dmDetail.do",
        data : {dmNo : dmNo},
        success : function(data){
            // console.log(data);
            $("#detailSender").text(data.sender);
            $("#detailDate").text(data.dmDate);
            $("#detailContent").text(data.dmContent);

            // ajax로 값을 바꾼 후 쪽지창 띄움 (쪽지 여러개 볼때 깜빡거리므로)
            $("#dmDetail").css("display", "flex");

            // 새로고침없이 읽음 여부 바뀜
            getReceiverDm();

            // 상세화면 표시 된 후 읽지않은 쪽지수 갱신
            const sendData = {type : "readCheck", sender : data.sender, receiver : data.receiver};
            ws.send(JSON.stringify(sendData));
        }
    })
}

function closeDetail(){
    $("#dmDetail").hide();
}

$(function(){
    // 페이지 로드 후
    // 헤더에서 읽지않은 쪽지수 불러오면서 실행하므로 중복됨
    // getReceiverDm();
    getSendDm();
});


