

window.onload = function()
{
  $.ajax({
    type : "GET",
    url : "http://monkeydoc.liadrinz.cn/rest/user",
    dataType:'json',
    processData: false,
    success : function(data) {
      $('#userNum').html(getHsonLength(data));
    },
    error : function(e){
        console.log(e.status);
        console.log(e.responseText);
    }
  });
    $.ajax({
      type : "GET",
      url : "http://monkeydoc.liadrinz.cn/rest/meta",
      dataType:'json',
      processData: false,
      success : function(data) {
        $('#docNum').html(getHsonLength(data));
      },
      error : function(e){
          console.log(e.status);
          console.log(e.responseText);
      }
});

	setInterval(Time, 1000);
	Time();

};

function Time() {
  var date = new Date();
  this.year = date.getFullYear();
  this.month = date.getMonth() + 1;
  this.date = date.getDate();
  this.day = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")[date.getDay()];
  this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
  this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
  this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
  var currentTime =  this.year + "." + this.month + "." + this.date + "<br>"+this.hour + ":" + this.minute + ":" + this.second  ;
  $("#week").html(this.day);
  $("#date")[0].innerHTML = currentTime + "<br>";
}

$('#Users').click(function(){
    window.location.replace("userManage.html");
});

$('#Docs').click(function(){
    window.location.replace("docManage.html");
});

function getHsonLength(json){
  var jsonLength=0;
  for (var i in json) {
      jsonLength++;
  }
  return jsonLength;
}