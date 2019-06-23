function checkUserName(){
    var username = document.getElementById("account").value;
    if(username==null||username==""){
        document.getElementById("userNameAlarm").innerHTML="用户名不能为空";
        return false;
    }else{
        document.getElementById("userNameAlarm").innerHTML="";
        return true;
    }
}
function checkPassword(){
    var password = document.getElementById("password").value;
    if(password==null||password==""){
        document.getElementById("passWordAlarm").innerHTML="密码不能为空";
        return false;
    }else{
        document.getElementById("passWordAlarm").innerHTML="";
        return true;
    }
}
function login() {
    if(checkUserName()&&checkPassword()){
        $.ajax({
            type:"post",
            url:"./checkAccount.do",
            data:$('#form-test').serialize(),
            success:function (result) {
                console.log(result)
                if(result.string=="success"){
                    var user = document.getElementById("user").value;
                    if(user=="student"){
                        window.location.href="../student/index.do";
                    }else if(user=="teacher"){
                        window.location.href="../teacher/index.do";
                    }else if(user=="admin"){
                        window.location.href="../admin/index.do";
                    }
                }else{
                    document.getElementById("checkAccountAlarm").innerHTML="用户名或密码错误";
                }
            },
            error:function () {
                alert("失败");
            }
        })
    }
}
function logout() {
    $.ajax({
        type:"get",
        url:"../login/logout.do",
        success:function (result) {
            if(result.string=="success"){
                window.location.href="../login/index.do";
            }
        },
        error:function () {
            alert("出现错误");
        }
    })
}
function keyEntry() {
    if(event.keyCode==13){
        document.getElementById("btn").click();
    }
}
function keyEntry8() {
    if(event.keyCode==13){
        document.getElementById("searchBtn").click();
    }
}
function keyEntry6() {
    if(event.keyCode==13){
        document.getElementById("searchCourseBtn").click();
    }
}

function keyEntry5() {
    if(event.keyCode==13){
        document.getElementById("searchBtn5").click();
    }
}

function init_load() {
    loadScore();
    setscoreRate();
}

function loadScore() {
    var url = window.location.href;
    var p = url.split('?')[1].split('=')
    var param={
        classId:p[1]
    }
    $.ajax({
        type:'get',
        data:param,
        url:'./score.do',
        success:function (result) {
            document.getElementById("tbody").innerHTML="";
            scoreFormat(result)
        },
        error:function () {
            alert("导入数据错误");
        }
    })
}

function scoreFormat(result) {
    document.getElementById("tbody").innerHTML=""
    var obj = result.seeStudentList
    for(var i=0;i<obj.length;i++){
        var message= '<td>'+(i+1)+'</td>\n' +
            '<td>'+obj[i].id+'</td>\n' +
            '<td>'+obj[i].name+'</td>\n' +
            '<td>'+obj[i].course+'</td>\n' ;
        if(obj[i].score==-1){
            message=message+'<td>-</td>\n'+
                '<td>-</td>\n'
        }else {
            message =message+'<td>'+obj[i].score+'</td>\n'+
                '<td>'+obj[i].rank+'</td>\n'
        }
        if(obj[i].feedback==0){
            message=message+
                '<td>\n' +
                '<button class="btn btn-warning">添加/修改</button>\n' +
                '</td>\n';
        }else {
            message=message+
                '<td>\n' +
                '<button class="btn" style="background: #ff500f">有反馈</button>\n' +
                '<button class="btn btn-warning">添加/修改</button>\n' +
                '</td>\n';
        }

        var tr = document.createElement("tr");
        tr.className="text-center middle";
        tr.innerHTML=message;
        document.getElementById("tbody").appendChild(tr);
    }
}

function search() {
    var keyWord = document.getElementById("search").value;
    var all = ["idType","nameType","dataType","javaType","sumType","avgType","rankType"];
    var name = "default";
    var type = "default";
    for(var i=0;i<all.length;i++){
        var value = document.getElementById(all[i]).value;
        if(value.trim()!="default"){
            name=all[i];
            type=value;
            break;
        }
    }
    var params ={
        'keyWord':keyWord,
        'name':name,
        'type':type
    };
    if(params.keyWord==null||params.keyWord.trim()==""){
        var flag=true;
        for(var i=0;i<all.length;i++){
            var value = document.getElementById(all[i]).value;
            if(value!="default"){
                flag=false;
                getScoreDataByType(all[i]);
                break;
            }
        }
        if(flag){
            loadScore();
        }
    }else{
        $.ajax({
            type:'get',
            data:params,
            url:"./search",
            success:function (result) {
                if(result.trim()!="error"){
                    document.getElementById("tbody").innerHTML="";
                    scoreFormat(result)
                }
            },
            error:function () {
                alert("错误");
            }
        })
    }
}
function keyEntry2() {
    if(event.keyCode==13){
        document.getElementById("searchBtn").click();
    }
}
function keyEntry7() {
    if(event.keyCode==13){
        document.getElementById("searchClassBtn").click();
    }
}
function keyEntry3() {
    if(event.keyCode==13){
        document.getElementById("searchBtn3").click();
    }
}
function keyEntry4() {
    if(event.keyCode==13){
        document.getElementById("searchBtn2").click();
    }
}
function stuIdChange() {
    getScoreDataByType("idType");
}
function nameChange() {
    getScoreDataByType("nameType");
}
function dataChange() {
    getScoreDataByType("dataType")
}
function javaChange() {
    getScoreDataByType("javaType")
}
function sumChange() {
    getScoreDataByType("sumType")
}
function avgChange() {
    getScoreDataByType("avgType")
}
function rankChange() {
    getScoreDataByType("rankType")
}

function checkType() {
    var all = ["idType","nameType","dataType","javaType","sumType","avgType","rankType"];
    var flag=true;
    for(var i=0;i<all.length;i++){
        var value = document.getElementById(all[i]).value;
        if(value.trim()!="default"){
            flag=false;
            getScoreDataByType(all[i]);
        }
    }
    if(flag){
        loadScore();
    }
}

function getScoreDataByType(name) {
    var all = ["idType","nameType","dataType","javaType","sumType","avgType","rankType"];
    for(var i=0;i<all.length;i++){
        if(all[i]!=name){
            document.getElementById(all[i]).value="default";
        }
    }
    var type = document.getElementById(name).value;
    var keyWord = document.getElementById("search").value;
    if(keyWord!=null&&keyWord.trim()!=""){
        search();
    }else {
        var params={
            'name':name,
            'type':type
        };
        $.ajax({
            type:'get',
            data:params,
            url:'./sortedscore',
            success:function (result) {
                document.getElementById("tbody").innerHTML="";
                scoreFormat(result)
            },
            error:function () {
                alert("错误");
            }
        })
    }

}

function setscoreRate(){
    var url = window.location.href;
    var p = url.split('?')[1].split('=')
    var param = {
        classId:p[1]
    }
    $.ajax({
        type:'get',
        data:param,
        url:'./rate.do',
        success:function (result) {
            var obj = result.hashMap
            pie(obj.A,obj.B,obj.C,obj.D)
        },
        error:function () {
            alert("error")
        }
    })
}

function pie(a,b,c,d) {
    var sum = a+b+c+d
    var arate = (a/sum).toFixed(3)
    var brate = (b/sum).toFixed(3)
    var crate = (c/sum).toFixed(3)
    var drate = (1-arate-brate-crate).toFixed(3)
    var chart = new Highcharts.Chart({
        chart: {
            renderTo:'container_1',
            type: 'pie',
            backgroundColor:"#EEF3FA",
            x:-200,
            height: 250,
            marginLeft:-150
        },
        credits: {
            enabled: false   //右下角不显示LOGO
        },
        title: {
            text: '',
        },
        subtitle: {
            text: '',
        },
        exporting: {//Highcharts 图表导出功能模块。
            enabled: false
        },
        colors: ['#E2214E', '#F7B52B', '#0749C3', '#66FE17',
            '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
        legend: {
            layout: 'vertical',
            floating: true,
            backgroundColor: '#EEF3FA',
            align: 'right',
            verticalAlign: 'top',
            y: 35,
            x: -20,
            itemMarginBottom :5,//图例的上下间距
            maxHeight: 200,
            symbolHeight: 14,//高度
            symbolWidth:14
        },

        plotOptions: {
            pie: {
                allowPointSelect: false,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true,
                symbolWidth: 24,
                point: {
                    events: {
                        legendItemClick: function (e) {
                            return false; // 直接 return false 即可禁用图例点击事件
                        }
                    }
                }
            }
        },
        series: [{
            data: [
                ['优秀'+'('+a+'名)' +'<br><span style="text-align:center;">'+arate+'%</span>', parseFloat(arate)],
                ['良好'+'('+b+'名)'+'<br><span style="text-align:center;">'+brate+'%</span>',     parseFloat(brate)],
                ['及格'+'('+c+'名)'+'<br><span style="text-align:center;">'+crate+'%</span>',parseFloat(crate)],
                ['不及格'+'('+d+'名)'+'<br><span style="text-align:center;">'+drate+'%</span>',     parseFloat(drate)]
            ]
        }]
    });
};
function query() {
    var id = document.getElementById("stuId").value;
    if(id==null||id.trim()==""){
        document.getElementById("stuIdAlarm").innerHTML="学号不能为空";
    }
}
function loadCourse() {
    document.getElementById("tbody").innerHTML=""
    $.ajax({
        type:'get',
        url:'./choose.do',
        success:function (result) {
            console.log(result)
            parseChoose1(result)
        },
        error:function () {
            alert("error")
        }
    })
}

function loadClasses() {
    $.ajax({
        type:'get',
        url:'./classes.do',
        success:function (result) {
            console.log(result)
            parseClasses1(result)
        },
        error:function () {
            alert("error")
        }
    })
}

function parseChoose1(result) {
    var obj = result.chooseList
    for(var i=0;i<obj.length;i++){
        var message= '<td>'+(i+1)+'</td>\n' +
            '<td>'+obj[i].stuId+'</td>\n' +
            '<td>'+obj[i].name+'</td>\n' +
            '<td>'+obj[i].classId+'</td>\n' +
            '<td>'+obj[i].course+'</td>\n' +
            '<td>'+obj[i].teacher+'</td>\n' ;
        if(obj[i].score==-1){
            message=message+'<td>-</td>\n'
        }else{
            message=message+'<td>'+obj[i].score+'</td>\n'
        }
        if(obj[i].feedback==0){
            message=message+ '<td>\n' +
                '<button class="btn" style="background: #ff733e" onclick="feedback()" id="feedback">反馈</button>\n' +
                '</td>\n';
        }else {
            message=message+ '<td>\n' +
                '<button class="btn" style="background: #2b542c" onclick="feedback()">已反馈</button>\n' +
                '</td>\n';
        }

        var tr = document.createElement("tr");
        tr.className="text-center middle";
        tr.innerHTML=message;
        document.getElementById("tbody").appendChild(tr);
    }
}

function parseClasses1(result) {
    var obj = result.classesList
    for(var i=0;i<obj.length;i++){
        var message= '<td>'+(i+1)+'</td>\n' +
            '<td>'+obj[i].classId+'</td>\n' +
            '<td>'+obj[i].courseId+'</td>\n' +
            '<td>'+obj[i].courseName+'</td>\n' +
            '<td>'+obj[i].teacher+'</td>\n' +
            '<td>'+obj[i].num+'</td>\n' +
            '<td>\n' +
            '<button class="btn seeStu" style="background: #ffaf18">查看学生</button>\n' +
            '</td>\n';
        var tr = document.createElement("tr");
        tr.className="text-center middle";
        tr.innerHTML=message;
        document.getElementById("tbody").appendChild(tr);
    }
}

function admin_init() {
    load_Student()
    load_teacher()
    load_classes()
    load_choose()
    load_course()
}

function load_course() {
    document.getElementById("tbody5").innerHTML=""
    $.ajax({
        type:'get',
        url:'./getClasses.do',
        success:function (result) {
            parseCourse(result)
        },
        error:function () {
            alert("error")
        }
    })
}

function load_Student() {
    document.getElementById("tbody").innerHTML=""
    $.ajax({
        type:'get',
        url:'./getStudents.do',
        success:function (result) {
            parseStudent(result)
        },
        error:function () {
            
        }
    })
}

function parseStudent(result) {
    document.getElementById("tbody").innerHTML=""
    var obj = result.studentList
    for(var i=0;i<obj.length;i++){
        var message=
            '<td>\n' +
            +(i+1)+'\n' +
            '</td>\n' +
            '<td>\n' +
            obj[i].id +
            '</td>\n' +
            '<td>\n' +
            obj[i].name +
            '</td>\n' +
            '<td>\n' +
            obj[i].password +
            '</td>\n'+
            '<td>\n' +
            '<button class="btn btn-danger">删除</button>\n' +
            '<button class="btn btn-stuChange" style="background: #ff8a09">修改</button>\n' +
            '</td>\n';
        var tr = document.createElement("tr");
        tr.innerHTML=message;
        document.getElementById("tbody").appendChild(tr);
    }
}
function load_teacher() {
    document.getElementById("tbody2").innerHTML=""
    $.ajax({
        type:'get',
        url:'./getTeachers.do',
        success:function (result) {
            parseTeachers(result)
        },
        error:function () {

        }
    })
}
function  parseTeachers(result) {
    var obj = result.teacherList
    for(var i=0;i<obj.length;i++){
        var message=
            '<td>\n' +
            +(i+1)+'\n' +
            '</td>\n' +
            '<td>\n' +
            obj[i].id +
            '</td>\n' +
            '<td>\n' +
            obj[i].name +
            '</td>\n' +
            '<td>\n' +
            obj[i].password +
            '</td>\n'+
            '<td>\n' +
            '<button class="btn btn-danger">删除</button>\n' +
            '<button class="btn btn-teaChange" style="background: #ff8a09">修改</button>\n' +
            '</td>\n';
        var tr = document.createElement("tr");
        tr.innerHTML=message;
        document.getElementById("tbody2").appendChild(tr);
    }
}

function  parseCourse(result) {
    document.getElementById("tbody5").innerHTML=""
    var obj = result.courseList
    for(var i=0;i<obj.length;i++){
        var message=
            '<td>\n' +
            +(i+1)+'\n' +
            '</td>\n' +
            '<td>\n' +
            obj[i].id +
            '</td>\n' +
            '<td>\n' +
            obj[i].name +
            '</td>\n' +
            '<td>\n' +
            '<button class="btn btn-danger">删除</button>\n' +
            '<button class="btn btn-warning" style="background: #ff8a09">修改</button>\n' +
            '</td>\n';
        var tr = document.createElement("tr");
        tr.innerHTML=message;
        document.getElementById("tbody5").appendChild(tr);
    }
}

function load_classes() {
    document.getElementById("tbody3").innerHTML=""
    $.ajax({
        type:'get',
        url:'./getClasses.do',
        success:function (result) {
            parseClasses(result)
        },
        error:function () {
            alert("错误")
        }
    })
}

function parseClasses(result) {
    document.getElementById("tbody3").innerHTML=""
    var obj = result.courseList
    for(var i=0;i<obj.length;i++){
        var message=
            '<td style="vertical-align: middle" rowspan="'+obj[i].adminClasses.length+'">\n' +
            +(i+1)+'\n' +
            '</td>\n' +
            '<td style="vertical-align: middle" rowspan="'+obj[i].adminClasses.length+'">\n' +
            obj[i].id +
            '</td>\n' +
            '<td style="vertical-align: middle" rowspan="'+obj[i].adminClasses.length+'">\n' +
            obj[i].name +
            '</td>\n'
        var oo = obj[i].adminClasses
        for(var j=0;j<oo.length;j++){
            message=message+
                '<td>\n' +
                oo[j].classId +
                '</td>\n'+
                '<td>\n' +
                oo[j].teacherId +
                '</td>\n'+
                '<td>\n' +
                oo[j].teacherName +
                '</td>\n'+
                '<td>\n' +
                '<button class="btn btn-danger">删除</button>\n' +
                '<button class="btn btn-warning">修改</button>\n' +

                '</td>\n';
            var tr = document.createElement("tr");
            tr.innerHTML=message;
            document.getElementById("tbody3").appendChild(tr);
            message="";
        }


    }
}

function load_choose() {
    document.getElementById("tbody4").innerHTML=""
    $.ajax({
        type:'get',
        url:'./getChooses.do',
        success:function (result) {
            parseChoose(result)
        },
        error:function () {
            alert("error")
        }
    })
}

function parseChoose(result) {
    document.getElementById("tbody4").innerHTML=""
    var obj = result.adminStudentList
    for(var i=0;i<obj.length;i++){
        var message=
            '<td style="vertical-align: middle" rowspan="'+obj[i].adminClass2s.length+'">\n' +
            +(i+1)+'\n' +
            '</td>\n' +
            '<td style="vertical-align: middle" rowspan="'+obj[i].adminClass2s.length+'">\n' +
            obj[i].id +
            '</td>\n' +
            '<td style="vertical-align: middle" rowspan="'+obj[i].adminClass2s.length+'">\n' +
            obj[i].name +
            '</td>\n'
        var oo = obj[i].adminClass2s
        for(var j=0;j<oo.length;j++){
            message=message+
                '<td>\n' +
                oo[j].classId +
                '</td>\n'+
                '<td>\n' +
                oo[j].course +
                '</td>\n'+
                '<td>\n' +
                oo[j].teacher +
                '</td>\n'+
                '<td>\n' +
                (oo[j].score==-1?'-':oo[j].score) +
                '</td>\n'+
                '<td>\n' +
                '<button class="btn btn-danger">删除</button>\n' +
                '<button class="btn btn-warning">修改</button>\n' +
                '</td>\n';
            var tr = document.createElement("tr");
            tr.innerHTML=message;
            document.getElementById("tbody4").appendChild(tr);
            message="";
        }
    }
}

function search2() {
    var url = window.location.href;
    var p = url.split('?')[1].split('=')
    var keyWord = document.getElementById("search2").value;
    var param={
        classId:p[1],
        keyWord:keyWord
    }
    $.ajax({
        type:'get',
        data:param,
        url:"./search.do",
        success:function (result) {
            scoreFormat(result)
        },
        error:function () {
            alert("error")
        }
    })
}

function studentAdd() {
    var stuId = document.getElementById("wq-id").value
    var name = document.getElementById("wq-stuName").value
    var password = document.getElementById("wq-password").value
    if(stuId!=null&&stuId!=""&&name!=null&&name!=""&&password!=null&&password!=""){
        document.getElementById("wq-id").innerHTML=""
        document.getElementById("wq-stuName").innerHTML=""
        document.getElementById("wq-password").innerHTML=""
        var param={
            stuId:stuId,
            name:name,
            password:password
        }
        $.ajax({
            type:'post',
            data:param,
            url:'./addStudent.do',
            success:function (result) {
                if(result.string=="success"){
                    load_Student()
                }
            },
            error:function () {
                alert("error")
            }
        })
    }else {
        alert("请输入学生信息")
    }
}
function search3() {
    var keyWord = document.getElementById("search3").value
    var param={
        keyWord:keyWord
    }
    if(keyWord==null||keyWord.trim()==""){
        load_Student()
    }else {
        $.ajax({
            type:'get',
            data:param,
            url:'./search.do',
            success:function (result) {
                parseStudent(result)
            },
            error:function () {
                alert("错误")
            }
        })
    }
}

function teacherAdd() {
    var id = document.getElementById("tea_id").value
    var name = document.getElementById("tea_Name").value
    var password = document.getElementById("tea_password").value
    if(id==null||id==""||name==null||name==""||password==null||password==""){
        alert("请输入教师信息")
    }else {
        document.getElementById("tea_id").innerHTML=""
        document.getElementById("tea_Name").innerHTML=""
        document.getElementById("tea_password").innerHTML=""
        var param = {
            id:id,
            name:name,
            password:password
        }
        $.ajax({
            type:'post',
            data:param,
            url:'./addTeacher.do',
            success:function (result) {
                if(result.string=='success'){
                    load_teacher()
                }
            },
            error:function () {
                alert("error")
            }
        })
    }
}

function searchTeacher() {
    var keyWord = document.getElementById("search5").value
    if(keyWord==null||keyWord==""){
        load_teacher()
    }else {
        var param={
            keyWord:keyWord
        }
        $.ajax({
            type:'get',
            data:param,
            url:'./searchTeacher.do',
            success:function (result) {
                document.getElementById("tbody2").innerHTML=""
                parseTeachers(result)
            },
            error:function () {
                alert("error")
            }
        })
    }
}

function classesAdd() {
    var classId = document.getElementById("class_id").value
    var teacher = document.getElementById("teacher").value
    var course = document.getElementById("course").value
    if(classId!=null&&classId!=""&&teacher!=null&&teacher!=""&&courseId!=null&&courseId!=""){
        document.getElementById("class_id").innerHTML=""
        document.getElementById("teacher").innerHTML=""
        document.getElementById("course").innerHTM=""
        var param = {
            classId:classId,
            teacher:teacher,
            course:course
        }
        $.ajax({
            type:'post',
            data:param,
            url:'./addClasses.do',
            success:function (result) {
                console.log(result)
                load_classes()
            },
            error:function () {
                alert("error")
            }
        })
    }else {
        alert("请将信息填写完整")
    }

}

function addCourse() {
    var courseId = document.getElementById("courseId").value
    var courseName = document.getElementById("courseName").value
    var param = {
        courseId : courseId,
        courseName:courseName
    }
    if(courseId!=null&&courseId.trim()!=""&&courseName!=null&&courseName.trim()!=""){
        $.ajax({
            type:'get',
            data:param,
            url:'./addCourse.do',
            success:function (result) {
                console.log(result)
                load_course()
            },
            error:function () {

            }
        })

    }else {
        alert("请输入课程信息")
    }

}

function searchCourse() {
    var keyWord = document.getElementById("searchCourse").value
    if(keyWord==null||keyWord.trim()==""){
        load_course()
    }else {
        var param={
            keyWord:keyWord
        }
        $.ajax({
            type:'get',
            data:param,
            url:'./searchCourse.do',
            success:function (result) {
                console.log(result)
                parseCourse(result)
            },
            error:function () {

            }
        })
    }
}

function search6() {
    var keyWord = document.getElementById("searchClass").value
    if(keyWord==null||keyWord.trim()==""){
        load_classes()
    }else {
        var param={
            keyWord:keyWord
        }
        $.ajax({
            type:'get',
            data:param,
            url:'./serachClasses.do',
            success:function (result) {
                console.log(result)
                parseClasses(result)
            },
            error:function () {

            }
        })
    }
}

function chooseAdd() {
    var student = document.getElementById("student-id").value
    var classes = document.getElementById("class-id").value
    if(student==null||student.trim()==""||classes==null||classes==""){
        alert("请填写选课信息")
    }else {
        var param={
            student:student,
            classes:classes
        }
        $.ajax({
            type:'get',
            data:param,
            url:'./addChoose.do',
            success:function (result) {
                if(result.string=="success"){
                    load_choose()
                }
            },
            error:function () {

            }
        })
    }
}

function searchChoose() {
    var keyWord = document.getElementById("search").value
    if(keyWord==null||keyWord.trim()==""){
        load_choose()
    }else {
        var param={
            keyWord:keyWord
        }
        $.ajax({
            type:'get',
            data:param,
            url:'./searchChoose.do',
            success:function (result) {
                parseChoose(result)
            },
            error:function () {
                alert("error")
            }
        })
    }
}

function feedback() {
    alert("已反馈成功")
}

function download() {
    var url = window.location.href;
    var p = url.split('?')[1].split('=')
    window.open("./download.do?classId="+p[1])
}