//当假如响应结果是json,则外面的格式必须有status 200
 {
    "description": "这是一个需要cookie的传json的post请求",
    "request": {
      "uri": "/postwithjsont",
      "method": "post",
      "json": {
        "name": "fefe"
      }
    },

    "response":{
      "status": 200,
      "json": {
        "name": "huhusuccess",
        "status": "2"
      }
    }
  }