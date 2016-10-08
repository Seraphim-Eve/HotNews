(function ($) {
    $(document).ready(function () {
        $("#blog_editor").validate({
            rules: {
                title: {
                    required: true,
                    maxlength: 10
                }
            },
            messages: {
                title: {
                    required: "<strong style='color: red;'>请输入标题!</strong>",
                    maxlength: "<strong style='color: red;'>你输入的标题超过10个字符!</strong>"
                }
            }
        });
    });

})(jQuery);