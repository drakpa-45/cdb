<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"  class="modal in" id="concirmationModel">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <span><b>Confirmation!</b></span>
            </div>
            <div class="modal-body form-horizontal">
                <div class="alert alert-info">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                            <span id="messages"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="actiontype=" id="actiontype">
                <input type="hidden" name="formId=" id="formId">
                <input type="hidden" name="targetId=" id="targetId">
                <input type="hidden" name="url=" id="url">
                <button type="button" class="btn btn-success" onclick="SubmitApproveVerifyApplicationDetials()">Yes</button>
                <button type="button" class="btn btn-warning" onclick="closemodel('concirmationModel')"><span class="fa fa-times"></span> No</button>
            </div>
        </div>
    </div>
</div>