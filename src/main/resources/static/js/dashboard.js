let reportTable = null;

/*
==================================================
PAGE LOAD
==================================================
*/
$(document).ready(function () {

    if ($("#reportTable").length) {

        reportTable = $("#reportTable").DataTable({
            pageLength: 25,
            ordering: true,
            searching: true,
            responsive: true,
            destroy: true
        });
    }

    showSection("dashboard");

});


/*
==================================================
SIDEBAR NAVIGATION
==================================================
*/
function showSection(section) {

    $("#dashboardSection").hide();
    $("#reportSection").hide();
    $("#lastUpdateSection").hide();
    $("#mismatchSection").hide();
    $("#statisticsSection").hide();
    $("#healthSection").hide();

    switch (section) {

        case "dashboard":
            $("#dashboardSection").show();
            break;

        case "report":
            $("#reportSection").show();
            break;

        case "lastUpdate":
            $("#lastUpdateSection").show();
            loadLastUpdate();
            break;

        case "mismatch":
            $("#mismatchSection").show();
            break;

        case "statistics":
            $("#statisticsSection").show();
            break;

        case "health":
            $("#healthSection").show();
            break;
    }
}


/*
==================================================
GENERATE REPORT
==================================================
*/
$(document).on("click", "#generateBtn", function () {

    let fromDate = $("#fromDate").val();
    let toDate = $("#toDate").val();
    let ty = $("#ty").val();

    if (!fromDate) {
        alert("Please Select From Date");
        return;
    }

    if (!toDate) {
        alert("Please Select To Date");
        return;
    }

    $("#processingDiv").show();
    $("#successDiv").hide();

    $("#generateBtn")
        .prop("disabled", true)
        .removeClass("btn-success")
        .addClass("btn-secondary");

    $.ajax({

        url: "/api/report",

        type: "POST",

        contentType: "application/json",

        data: JSON.stringify({
            fromDate: fromDate,
            toDate: toDate,
            ty: ty
        }),

        success: function (response) {

            reportTable.clear();

            response.forEach(function (r) {

                let status =
                    Number(r.numFilesCnt || 0) === Number(r.jsonCount || 0)
                        ? '<span class="badge bg-success">MATCHED</span>'
                        : '<span class="badge bg-danger">MISMATCH</span>';

                reportTable.row.add([
                    r.dt || '',
                    r.numFiles || 0,
                    r.fileNum || 0,
                    r.numFilesCnt || 0,
                    r.dt2 || '',
                    r.fileNumber || 0,
                    r.jsonCount || 0,
                    status
                ]);

            });

            reportTable.draw();

            $("#processingDiv").hide();

            $("#successDiv")
                .html("Report Generated Successfully")
                .show();

            $("#generateBtn")
                .prop("disabled", false)
                .removeClass("btn-secondary")
                .addClass("btn-success");
        },

        error: function (xhr) {

            $("#processingDiv").hide();

            $("#generateBtn")
                .prop("disabled", false)
                .removeClass("btn-secondary")
                .addClass("btn-success");

            alert("Error while generating report");

            console.error(xhr.responseText);
        }

    });

});


/*
==================================================
LAST UPDATE DASHBOARD
==================================================
*/
function loadLastUpdate() {

    $.ajax({

        url: "/api/last-update",

        type: "GET",

        success: function (response) {

            let cardHtml = "";
            let tableHtml = "";

            response.forEach(function (r) {

                cardHtml += `
                    <div class="col-md-2 mb-3">
                        <div class="card shadow-sm update-card"
                             onclick="triggerDownload('${r.ty}')">

                            <div class="card-body text-center">

                                <h6>${r.ty}</h6>

                                <strong>${r.maxDate}</strong>

                                <hr>

                                <small class="text-primary fw-bold">
                                    Click To Run
                                </small>

                            </div>

                        </div>
                    </div>
                `;

                tableHtml += `
                    <tr>
                        <td>${r.ty}</td>
                        <td>${r.maxDate}</td>
                    </tr>
                `;
            });

            $("#updateCards").html(cardHtml);

            $("#lastUpdateTable tbody").html(tableHtml);

        },

        error: function () {

            console.log("Unable to load last update data");

        }

    });
}


/*
==================================================
DOWNLOAD PDF
==================================================
*/
function downloadPdf() {
    window.location.href = "/api/pdf";
}


/*
==================================================
DOWNLOAD EXCEL
==================================================
*/
function downloadExcel() {
    window.location.href = "/api/excel";
}


/*
==================================================
DASHBOARD SUMMARY
==================================================
*/
function loadDashboardSummary() {

    $.ajax({

        url: "/api/dashboard-summary",

        type: "GET",

        success: function (response) {

            $("#totalReturns").text(response.totalReturns);
            $("#matchedCount").text(response.matchedCount);
            $("#mismatchCount").text(response.mismatchCount);
            $("#lastUpdatedDate").text(response.lastUpdatedDate);
        }

    });
}


/*
==================================================
RUN DOWNLOAD API
==================================================
*/
function triggerDownload(ty) {

    let apiMap = {

        "CM8": "/common/gstr/CM8",
        "payment": "/common/gstr/payment",

        "R1": "/common/gstr/R1",
        "R1A": "/common/gstr/R1A",

        "R2B": "/common/gstr/R2B",
        "R3B": "/common/gstr/R3B",

        "R4": "/common/gstr/R4",
        "R5": "/common/gstr/R5",
        "R6": "/common/gstr/R6",
        "R7": "/common/gstr/R7",
        "R8": "/common/gstr/R8",

        "R9": "/common/gstr/R9",
        "R9A": "/common/gstr/R9A",
        "R9C": "/common/gstr/R9C",

        "R98A": "/common/gstr/R98A",

        "R10": "/common/gstr/R10",
        "R11": "/common/gstr/R11"
    };

    let url = apiMap[ty];

    if (!url) {

        alert("API not configured for : " + ty);
        return;
    }

    if (!confirm("Run Download Process For " + ty + " ?")) {
        return;
    }

    $.ajax({

        url: url,

        type: "GET",

        beforeSend: function () {

            $("body").append(`
                <div id="loadingOverlay"
                     style="
                     position:fixed;
                     top:0;
                     left:0;
                     width:100%;
                     height:100%;
                     background:rgba(0,0,0,.5);
                     z-index:9999;
                     display:flex;
                     justify-content:center;
                     align-items:center;
                     color:white;
                     font-size:22px;">
                     Processing ${ty} ...
                </div>
            `);
        },

        success: function (response) {

            $("#loadingOverlay").remove();

            alert("Success\n\n" + response);

            loadLastUpdate();
        },

        error: function (xhr) {

            $("#loadingOverlay").remove();

            alert("Failed : " + xhr.responseText);
        }

    });
}