(function ($) {
    $(document).ready(function () {

        //check mail
        /*$("#email").blur(function () {
            check();
        });*/

        //check mail
        $("#register").submit(function () {
            var value = $("#email").val();
            var flag = false;
            if (validateEmail(value)) {
                $.ajax({
                    method: "POST",
                    url: "check.do",
                    async: false, //为了赋值flag变量的值
                    data: {email: value},
                    success: function(data) {
                        //console.log(data);
                        if (data == "true") {

                            $.validator.methods.email = function(value, element) {
                                return this.optional(element) || false;
                            };
                            $("#email-error").text("该用户名已有人使用.改用其他用户名?");

                            //$.validator.methods.email.messages("该用户名已有人使用.改用其他用户名?");

                        }
                    }
                });
            }
            return flag;
        });

        //check email
        $.validator.methods.email = function(value, element) {
            return this.optional(element) || validateEmail(value);
        };

        //validate email
        function validateEmail(email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        jQuery.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success has-feedback').addClass('has-error has-feedback');
                $(element).closest('.form-group').find('i.fa').remove();
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error has-feedback').addClass('has-success has-feedback');
                $(element).closest('.form-group').find('i.fa').remove();
            }
        });

        $("#register").validate({
            rules: {
                nickname: {
                    required: true,
                    maxlength: 10
                },
                email: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 12
                },
                repeatPassword: {
                    required: true,
                    minlength: 8,
                    maxlength: 12,
                    equalTo: "#password"
                }
            },
            messages: {
                nickname: {
                    required: "请输入昵称!",
                    maxlength: "你输入的昵称超过10个字符!"
                },
                email: "请输入有效的电子邮箱地址!",
                password: {
                    required: "请输入密码!",
                    minlength: "输入的密码小于8位!",
                    maxlength: "输入的密码大于12位!"
                },
                repeatPassword: {
                    required: "两个密码不匹配。是否重试?",
                    minlength: "输入的密码小于8位!",
                    maxlength: "输入的密码小于12位!",
                    equalTo: "两个密码不匹配。是否重试?"
                }
            }
        });

        /*$("#nickname").popover({
            content: '你可以使用中文和英文.',
            placement: 'left',
            trigger: 'focus'
        });

        $("#email").popover({
            content: '例如: example@mail.com',
            placement: 'left',
            trigger: 'focus'
        });

        $("#password").popover({
            title: '密码强度:',
            content: '请至少使用 8 个字符.',
            placement: 'left',
            trigger: 'focus'
        });

        $("#nickname").blur(function () {
            $("#nickname").popover('hide');
        });

        $("#email").blur(function () {
            $("#email").popover('hide');
        });

        $("#password").blur(function () {
            $("#password").popover('hide');
        });*/

    });
})(jQuery);