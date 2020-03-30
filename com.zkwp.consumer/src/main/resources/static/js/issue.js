$("#uploadfile").fileinput({
    language: 'zh', //设置语言
    textEncoding : "UTF-8",//文本编码
    uploadUrl: "../../consumer/issueCenter/issueIndex/uploadFile", //上传的地址
    allowedFileExtensions: ['avi', 'mpg', 'mpeg', 'wmv', 'mov', 'rm', 'ram', 'swf', 'flv', 'mp4'], //接收的视频文件后缀
    uploadAsync: true, //默认异步上传
    showUpload: true, //是否显示上传按钮
    showRemove : true, //显示移除按钮
    showPreview : true, //是否显示预览
    showCaption: false,//是否显示标题
    browseClass: "btn btn-primary", //按钮样式
    dropZoneEnabled: true,//是否显示拖拽区域
    maxFileCount: 3, //表示允许同时上传的最大文件个数
    enctype: 'multipart/form-data',
    maxFileSize:100 *1024 *1024,
    messages: {maxFileSize:'文件上传的最大大小为 100MB',acceptFileTypes:'此文件是不支持的图片格式'},
    validateInitialCount:true
}).on("fileuploaded", function (event, data, previewId, index) {
});


