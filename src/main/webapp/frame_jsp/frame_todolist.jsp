<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<!-- TO DO List -->
<div class="box box-primary">
    <div class="box-header">
        <i class="ion ion-clipboard"></i>
        <h3 class="box-title">项目内容</h3>
        <%--        <div class="box-tools pull-right">
                    <ul class="pagination pagination-sm inline">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>--%>
    </div><!-- /.box-header -->
    <div class="box-body">
        <ul class="todo-list">
            <li>
                <!-- drag handle -->
                <span class="handle">
                                                <i class="fa fa-ellipsis-v"></i>
                                                <i class="fa fa-ellipsis-v"></i>
                                            </span>
                <!-- checkbox -->
                <%--<input type="checkbox" value="" name=""/>--%>
                <!-- todo text -->
                <span class="text">环境参数采集</span>
                <!-- Emphasis label -->
                <small class="label label-success"><i class="fa fa-clock-o"></i> 已完成</small>
                <!-- General tools such as edit or delete-->
                <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                </div>
            </li>
            <li>
                                            <span class="handle">
                                                <i class="fa fa-ellipsis-v"></i>
                                                <i class="fa fa-ellipsis-v"></i>
                                            </span>
                <%--<input type="checkbox" value="" name=""/>--%>
                <span class="text">设备工作状态监控</span>
                <small class="label label-success"><i class="fa fa-clock-o"></i> 已完成</small>
                <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                </div>
            </li>
            <li>
                                            <span class="handle">
                                                <i class="fa fa-ellipsis-v"></i>
                                                <i class="fa fa-ellipsis-v"></i>
                                            </span>
                <%--<input type="checkbox" value="" name=""/>--%>
                <span class="text">火灾报警系统</span>
                <small class="label label-success"><i class="fa fa-clock-o"></i> 已完成</small>
                <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                </div>
            </li>
            <li>
                                            <span class="handle">
                                                <i class="fa fa-ellipsis-v"></i>
                                                <i class="fa fa-ellipsis-v"></i>
                                            </span>
                <%--<input type="checkbox" value="" name=""/>--%>
                <span class="text">Web数据同步</span>
                <small class="label label-primary"><i class="fa fa-clock-o"></i> 进行中</small>
                <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                </div>
            </li>
            <li>
                                            <span class="handle">
                                                <i class="fa fa-ellipsis-v"></i>
                                                <i class="fa fa-ellipsis-v"></i>
                                            </span>
                <%--<input type="checkbox" value="" name=""/>--%>
                <span class="text">数据分析及可视化</span>
                <small class="label label-warning"><i class="fa fa-clock-o"></i> 规划中</small>
                <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                </div>
            </li>
        </ul>
    </div><!-- /.box-body -->
    <div class="box-footer clearfix no-border">
        <button class="btn btn-default pull-right"><i class="fa fa-plus"></i> 添加</button>
    </div>
</div>
<!-- /.box -->
