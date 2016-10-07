(function ($) {
    $(document).ready(function () {
        var blog_id = $("#blog_id").val();

        if (blog_id != '' && blog_id != null) {
            $("#good").click(function () {
                $.ajax({
                    url: "blog_good.do",
                    data: "blog_id=" + blog_id,
                    type: "POST",
                    success: function (data) {
                        if ("" != data) {
                            if (data == "100") {
                                //已经点过赞
                                $("#good").attr("disabled", "disabled");
                                $("#good_warn").html("<p style='color: red;'>你已经点过赞了!</p>");
                            } else if (data == "200") {
                                //点赞成功
                                $("#good").attr("disabled", "disabled");
                                $("#good_warn").html("<p style='color: red;'>点赞成功!</p>");
                            } else {
                                //点赞失败
                                $("#good_warn").html("<p style='color: red;'>点赞失败!</p>");
                            }
                        }
                    }
                });
            });
        }
    });
})(jQuery);