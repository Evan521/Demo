var vm =new Vue({
        el:'#index',
        data:{
            userObj:{},
            loginDo:true,
            regist:false,
            password2:''
        },
        methods:{
            login:function () {
                var formData = JSON.stringify(this.userObj);
                this.$http.post('/login',formData,).then(function (res) {
                    if(res.body.status==true){
                        this.$alert('Welcome', '猪猪刘全有的空间!!', {
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$message({
                                    type: 'info',
                                    message: `action: ${ action }`
                                });
                            }
                        });
                    }else{
                        this.$message({
                            type:'error',
                            message:'用户名或密码错误',
                        });
                    }
                });
            },
            reset:function () {
                this.userObj.userName='';
                this.userObj.password='';

            },
            register:function () {
                this.loginDo = false;
                this.regist = true;
            },
            back:function () {
                this.loginDo = true;
                this.regist = false;
            },
            registUser:function () {
                var formData = JSON.stringify(this.userObj);
                if(this.userObj.password!=this.password2) {
                    this.$message({
                        type: 'error',
                        message: '两次密码输入不一致',
                    });
                }else{
                    this.$http.post('/register',formData,).then(function (res) {
                        if(res.body.status==true){
                            this.$message({
                                type:'success',
                                message:'注册成功',
                            });

                        }else{
                            this.$message({
                                type:'error',
                                message:'注册失败',
                            });
                        }
                    });
                }

            }

        }
    })
