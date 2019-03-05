/*后台视频点击加载*/
$("#admin-video").click(function () {
    /* 点击前先移除之前所选*/
    $("a").removeClass("active-menu")

    $(this).addClass("active-menu")
    $("#page-wrapper").load("admin-video.html")
});

$("#comments").click(function () {
    /* 点击前先移除之前所选*/
    $("a").removeClass("active-menu")

    $(this).addClass("active-menu")
    $("#page-wrapper").load("comments.html")
});

$("#category").click(function () {
    /* 点击前先移除之前所选*/
    $("a").removeClass("active-menu")

    $(this).addClass("active-menu")
    $("#page-wrapper").load("category.html")
});