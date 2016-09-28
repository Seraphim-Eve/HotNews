<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/28
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>博客查看</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="nprogress/nprogress.css">
    <script src="js/jquery.min.js"></script>
    <script src="nprogress/nprogress.js"></script>

    <style>
        H2 {
            font-family: "Palatino Linotype", "Book Antiqua", Palatino, Helvetica, STKaiti, SimSun, serif;
            font-weight: bold;
            margin-bottom: 60px;
            margin-bottom: 40px;
            padding: 5px;
            border-bottom: 2px LightGrey solid;
            width: 98%;
            line-height: 150%;
            color: #666666;
        }

        H3 {
            font-family: "Palatino Linotype", "Book Antiqua", Palatino, Helvetica, STKaiti, SimSun, serif;
            font-weight: bold;
            margin-top: 40px;
            margin-bottom: 30px;
            border-bottom: 1px LightGrey solid;
            width: 98%;
            line-height: 150%;
            color: #666666;
        }

    </style>
</head>
<body>
    <br/>

    <div class="container">
        <div class="row">
            <div class="col-md-12" role="main">
                <div class="col-md-9">
                    <!-- 内容区 -->
                    <div id="content" style="padding: 2% 8% 5% 8%; border: 1px solid LightGrey; border-radius: 4px;">
                        <h2>收回前一篇文章</h2>

                        <!-- TODO 博客内容 -->
                        <p>写前一篇文章的时候，头脑比较热。现在想起来，也不知道自己为什么会说出那样的话来，为什么会有那样的想法。最后是真是假，还有一些半成品和猜忌，都一股脑的倒了出来。</p>

                        <p>今天回到公司，看见 manager 诚恳的眼神，被一群低调的同事所围绕，听取我对某些工具的抱怨和改进意见，向我表示抱歉，解释并且确认需要改进的地方。我发现，满头的怨气顿时全没了。这是一群挺可爱的同事……</p>

                        <p>也许，是经历了这么多风雨和奇葩的人际关系之后，很难不猜忌其它人的想法吧，很难以轻松的心情相待。一朝被蛇咬，十年怕井绳。有些事情不说清楚，就憋在心里了。人和人的了解需要时间，才来两个月，都不怎么熟悉，难免因为语言上的差异而产生误会。如果说有人封闭和压抑的话，那恐怕是我自己吧。这里的天空其实挺广阔，是我自己没有看到。</p>

                        <p>实话说在这两个月里面，我是真心欣赏微软的很多方面，享受自己所做的事情的时候还是很多，可是那天折腾得郁闷了就都忘了说好的方面。如果要我用两个字来概括对这个公司的第一印象，我能想到的两个字就是：成熟。我能感觉到，他们其中一些人会是很好的朋友。</p>

                        <p>刚说错那么多话，这里就不好意思多说了。</p>

                        <p>（有人在关心是不是领导给了我压力，所以改口了。我王垠有这么怕事吗？话说如果领导真给了我压力，那我还反倒不改了。我又不靠微软这口饭活着 :P 我是真心觉得自己太急太冲动了，本着尊重事实的原则，所以才撤回的。）</p>

                    </div>
                </div>

                <div class="col-md-3">

                    <!-- 评论区 -->
                    <div  id="comment" style="padding: 2% 8% 5% 8%; border: 1px solid LightGrey; border-radius: 4px; overflow:auto">
                        <h3>评论</h3>

                        <%--<div class="form-group">
                            <label for="comment">Comment:</label>
                            <textarea class="form-control" rows="5" id="comment"></textarea>
                        </div>--%>

                        <!-- TODO 评论内容 -->
                        <div class="col-md-12">
                            <div class="col-md-8 pull-left">
                                <input id="comments" type="text" class="form-control" placeholder="输入评论" maxlength="50">
                            </div>
                            <div class="col-md-4 pull-right">
                                <button id="commentSub" type="button" class="btn btn-defualt" style="background-color: white; border-color: #ccc;">评论</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <br/>

        <!-- 赞区域 -->
        <div class="row">
            <div class="col-md-12" align="center">
                <!-- TODO 赞验证(不能重复赞) -->
                <button type="button" class="btn btn-default btn-lg" disabled="disabled">
                    <span class="glyphicon glyphicon-thumbs-up"></span>
                </button>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        //评论区与内容区高度同步
        $("#comment").css("height", $("#content").css("height"));

        NProgress.start();

        $(document).ready(function () {
            NProgress.done();
        });
    </script>

</body>
</html>
