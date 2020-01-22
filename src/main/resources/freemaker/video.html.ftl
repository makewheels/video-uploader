<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <link href="https://cdn.bootcss.com/video.js/7.6.5/video-js.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/video.js/7.6.5/video.min.js"></script>
    <script src="https://cdn.bootcss.com/video.js/7.6.5/lang/en.js"></script>
    <script src="https://cdn.bootcss.com/video.js/7.6.5/lang/zh-CN.js"></script>

</head>

<body>
<video id="videoId" class="video-js vjs-default-skin" controls preload="auto"
        <#--       poster="http://vjs.zencdn.net/v/oceans.png"-->
>
    <source src="${videoUrl}" type="video/mp4">
</video>
<h3>${videoName}</h3>
<script>
    document.onreadystatechange
    var player = videojs('videoId', {
        width: 1088,
        height: 612
    });
</script>
</body>
</html>