# LoginServers
登陆、注册和修改密码的API。
用Android Studio写登陆注册功能时可以用POST功能直接调用。
服务器返回json，成功就是success，失败就是false。
调用方法如下：

    private void postRequest(String name,String pwd, String phone)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormEncodingBuilder()
                .add("username",name)
                .add("pswd",pwd)
                .add("phone",phone)
                .add("method","okhttpreg")
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://39.96.191.xx:8080/LoginServers/RegisterAction")//IP是我的阿里云
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        System.out.println(str.substring(17, 24));
                        if(str.substring(17, 24).equals("success")) {
                            Intent intent = new Intent();
                            intent.setClass(RegisterActivity.this, Reg_successActivity.class);
                            RegisterActivity.this.startActivity(intent);
                        } else{
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this, "账号已存在", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Log.d("RegisterActivity", "fffffff1");
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    Log.d("RegisterActivity", "fffffff2");
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
