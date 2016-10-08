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

        $("#login").validate({
            rules: {
                email: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 12
                }
            },
            messages: {
                email: "请输入有效的电子邮箱地址!",
                password: {
                    required: "请输入您的密码!",
                    minlength: "输入的密码小于8位!",
                    maxlength: "输入的密码大于12位!"
                }
            }

        });

        //jquery email组件正则匹配验证
        $.validator.methods.email = function(value, element) {
            return this.optional(element) || validateEmail(value)
        };

        //validate email
        function validateEmail(email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }
    });
})(jQuery);