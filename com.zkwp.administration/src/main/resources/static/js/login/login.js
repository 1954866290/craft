layui.use(['layer', 'form', 'jquery'], function() {
    let $ = layui.jquery,
        layer = layui.layer,
        form = layui.form;
    
    form.on('submit(login)', function () {
        let index = layer.load();
        $.ajax({
            type: 'post',
            url: nginx_url + '/doLogin',
            data: {
                'loginId': $('#loginId').val(),
                'password': $('#password').val()
            },
            dataType: 'json',
            async: false,
            success: function (result) {
                console.log(result);
                if (result.status === "200") {
                    $.cookie("loginId", result.loginId);
                    window.location.href = '../../craft/administration/index';
                }
            }
        });
        return false;
    });
});
