(function ($) {
    $("#blog_editor").validate({
        rules: {
            blog_title: {
                required: true,
                maxlength: 10
            }
        },
        messages: {
            blog_title: {
                required: "请输入昵称!",
                maxlength: "你输入的昵称超过10个字符!"
            }
        }
    });

})(jQuery);