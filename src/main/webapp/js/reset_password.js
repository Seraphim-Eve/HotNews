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

        //检查原密码是否正确
        $.validator.addMethod("isCorrect", function(value, element) {
            var flag = false;
            $.ajax({
                method: "POST",
                url: "check_password.do",
                async: false,
                data: {password: value},
                success: function(data) {
                    if (data == "true") {
                        flag = true;
                    }
                }
            });
            return flag;
        }, "输入的原始密码错误!");

        $("#reset_password").validate({
            rules: {
                s_password: {
                    required: true,
                    minlength: 8,
                    maxlength: 12,
                    isCorrect: true
                },
                n_password: {
                    required: true,
                    minlength: 8,
                    maxlength: 12
                },
                repeat_n_password: {
                    required: true,
                    minlength: 8,
                    maxlength: 12,
                    equalTo: "#n_password"
                }
            },
            messages: {
                s_password: {
                    required: "请输入密码!",
                    minlength: "输入的密码小于8位!",
                    maxlength: "输入的密码大于12位!"
                },
                n_password: {
                    required: "请输入密码!",
                    minlength: "输入的密码小于8位!",
                    maxlength: "输入的密码大于12位!"
                },
                repeat_n_password: {
                    required: "两个密码不匹配。是否重试?",
                    minlength: "输入的密码小于8位!",
                    maxlength: "输入的密码小于12位!",
                    equalTo: "两个密码不匹配。是否重试?"
                }
            }
        });
    });
})(jQuery);