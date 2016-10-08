(function ($) {
    $(document).ready(function() {

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

        $("#effective_code").validate({
            rules: {
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
    });
})(jQuery);