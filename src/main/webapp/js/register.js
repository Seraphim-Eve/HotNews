(function ($) {
    $(document).ready(function () {

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

        $("#nickname").popover({
            content: '你可以使用中文和英文.',
            placement: 'left'
        });

        $("#email").popover({
            content: '例如: example@mail.com',
            placement: 'left'
        });

        $("#password").popover({
            title: '密码强度:',
            content: '请至少使用 8 个字符.',
            placement: 'left'
        });

        $("#nickname").mouseleave(function () {
            $("#nickname").popover('hide');
        });

        $("#email").mouseleave(function () {
            $("#email").popover('hide');
        });

        $("#password").mouseleave(function () {
            $("#password").popover('hide');
        });

    });
})(jQuery)