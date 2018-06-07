<div class="box box-danger" id="loading-example">
    <div class="box-header">
        <!-- tools box -->
        <div class="pull-right box-tools">
            <button class="btn btn-danger btn-sm refresh-btn" data-toggle="tooltip" title="Reload"><i class="fa fa-refresh"></i></button>
            <button class="btn btn-danger btn-sm" data-widget='collapse' data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
            <button class="btn btn-danger btn-sm" data-widget='remove' data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
        </div><!-- /. tools -->
        <i class="fa fa-cloud"></i>

        <h3 class="box-title">Server Load</h3>
    </div><!-- /.box-header -->
    <div class="box-body no-padding">
        <div class="row">
            <div class="col-sm-7">
                <!-- bar chart -->
                <div class="chart" id="bar-chart" style="height: 250px;"></div>
            </div>
            <div class="col-sm-5">
                <div class="pad">
                    <!-- Progress bars -->
                    <div class="clearfix">
                        <span class="pull-left">Bandwidth</span>
                        <small class="pull-right">10/200 GB</small>
                    </div>
                    <div class="progress xs">
                        <div class="progress-bar progress-bar-green" style="width: 70%;"></div>
                    </div>

                    <div class="clearfix">
                        <span class="pull-left">Transfered</span>
                        <small class="pull-right">10 GB</small>
                    </div>
                    <div class="progress xs">
                        <div class="progress-bar progress-bar-red" style="width: 70%;"></div>
                    </div>

                    <div class="clearfix">
                        <span class="pull-left">Activity</span>
                        <small class="pull-right">73%</small>
                    </div>
                    <div class="progress xs">
                        <div class="progress-bar progress-bar-light-blue" style="width: 70%;"></div>
                    </div>

                    <div class="clearfix">
                        <span class="pull-left">FTP</span>
                        <small class="pull-right">30 GB</small>
                    </div>
                    <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 70%;"></div>
                    </div>
                    <!-- Buttons -->
                    <p>
                        <button class="btn btn-default btn-sm"><i class="fa fa-cloud-download"></i> Generate PDF</button>
                    </p>
                </div><!-- /.pad -->
            </div><!-- /.col -->
        </div><!-- /.row - inside box -->
    </div><!-- /.box-body -->
    <div class="box-footer">
        <div class="row">
            <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                <input type="text" class="knob" data-readonly="true" value="80" data-width="60" data-height="60" data-fgColor="#f56954"/>
                <div class="knob-label">CPU</div>
            </div><!-- ./col -->
            <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                <input type="text" class="knob" data-readonly="true" value="50" data-width="60" data-height="60" data-fgColor="#00a65a"/>
                <div class="knob-label">Disk</div>
            </div><!-- ./col -->
            <div class="col-xs-4 text-center">
                <input type="text" class="knob" data-readonly="true" value="30" data-width="60" data-height="60" data-fgColor="#3c8dbc"/>
                <div class="knob-label">RAM</div>
            </div><!-- ./col -->
        </div><!-- /.row -->
    </div><!-- /.box-footer -->
</div><!-- /.box -->
