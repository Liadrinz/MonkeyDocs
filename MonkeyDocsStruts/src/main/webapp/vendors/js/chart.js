$(function() {
  
  var date = new Date();
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var userNum = [];
  var docNum = [];
  var timelist = [];
  for(var i = 1; i <= month; i++)
  {
    userNum[year+"-0"+i] = 0;
    docNum[year+"-0"+i] = 0;
    timelist.push(year+"-0"+i);
  }

  
  $.ajax({
    type : "GET",
    url : "http://monkeydoc.liadrinz.cn/rest/user",
    dataType:'json',
    processData: false,
    success : function(data) {
      $.each(data,function(idx,obj){
        userNum[timestampToTime(obj.createTime).substring(0,7)]++;
      });

      var userData = []
      for(var key in userNum)
      {
        userData.push(add(userNum,key));
      }

    var areaDataForUser = {
      labels: timelist,
      datasets: [{
      label: '用户总数',
      data: userData,
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)'
      ],
      borderColor: [
        'rgba(255,99,132,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
      ],
      borderWidth: 1,
      fill: 'origin', // 0: fill to 'origin'
      fill: '+2', // 1: fill to dataset 3
      fill: 1, // 2: fill to dataset 1
      fill: false, // 3: no fill
      fill: '-2' // 4: fill to dataset 2
    }]
  };

  $.ajax({
    type : "GET",
    url : "http://monkeydoc.liadrinz.cn/rest/meta",
    dataType:'json',
    processData: false,
    success : function(data) {

      $.each(data,function(idx,obj){
        docNum[timestampToTime(obj.createTime).substring(0,7)]++;
      });

      var docData = []
      for(var key in docNum)
      {
        docData.push(add(docNum,key));
      }

      var areaDataForDoc = {
        labels: timelist,
        datasets: [{
          label: '文档总数',
          data: docData,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1,
          fill: 'origin', // 0: fill to 'origin'
          fill: '+2', // 1: fill to dataset 3
          fill: 1, // 2: fill to dataset 1
          fill: false, // 3: no fill
          fill: '-2' // 4: fill to dataset 2
        }]
      };
    
      var areaOptions = {
        plugins: {
          filler: {
            propagate: true
          }
        }
      }
    
      // Get context with jQuery - using jQuery's .get() method.
    
      if ($("#areaChart1").length) {
        var areaChartCanvas = $("#areaChart1").get(0).getContext("2d");
        var areaChart = new Chart(areaChartCanvas, {
          type: 'line',
          data: areaDataForUser,
          options: areaOptions
        });
      }
    
      if ($("#areaChart2").length) {
        var areaChartCanvas = $("#areaChart2").get(0).getContext("2d");
        var areaChart = new Chart(areaChartCanvas, {
          type: 'line',
          data: areaDataForDoc,
          options: areaOptions
        });
      }
    },
    error : function(e){
        console.log(e.status);
        console.log(e.responseText);
    }
  });

  

    },
    error : function(e){
        console.log(e.status);
        console.log(e.responseText);
    }
});



});

function timestampToTime(timestamp) {
  var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  Y = date.getFullYear() + '-';
  M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  D = date.getDate() + ' ';
  h = date.getHours() + ':';
  m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
  s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
  return Y+M+D+h+m+s;
  }

function add(arr,key)
{
    var total = 0;
    for(var keys in arr)
    {
      if( parseInt(keys.substring(keys.indexOf("-")+1,keys.length)) <= parseInt(key.substring(key.indexOf("-")+1,key.length)) )
        total += arr[keys]
    }
    return total;
}