(function ($){
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

        //正则验证邮箱
        $.validator.addMethod("regex", function(value, element) {
            return validateEmail(value);
        }, "请输入有效的电子邮箱地址!");

        //验证邮箱是否存在
        $.validator.addMethod("isExists", function(value, element) {
            var flag = false;
            if (validateEmail(value)) {
                $.ajax({
                    method: "POST",
                    url: "check.do",
                    async: false, //为了赋值flag变量的值
                    data: {email: value},
                    success: function(data) {
                        if (data == "true") {
                            //邮箱存在
                            flag = true;
                        }
                    }
                });
            }
            return flag;
        }, "找不到您的Email帐户,请重试.");


        //validate email
        function validateEmail(email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        $("#forgot").validate({
            rules: {
                email: {
                    required: true,
                    regex: true,
                    isExists: true
                }
            },
            messages: {
                email: {
                    required: "请输入有效的电子邮箱地址!"
                }
            }
        });

    });
})(jQuery);