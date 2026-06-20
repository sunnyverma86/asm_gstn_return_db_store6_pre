
let reportTable = null;

/*
==================================================
PAGE LOAD
==================================================
*/
$(document).ready(function () {

    reportTable = $('#reportTable').DataTable({
        pageLength: 25,
        ordering: true,
        searching: true,
        responsive: true,
        destroy: true
    });

    showSection('dashboard');

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
$(document).on('click', '#generateBtn', function () {

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

        url: '/api/report',

        type: 'POST',

        contentType: 'application/json',

        data: JSON.stringify({

            fromDate: fromDate,
            toDate: toDate,
            ty: ty

        }),

        success: function (response) {

            reportTable.clear();

            response.forEach(function (r) {

                let numFilesCnt =
                    r.numFilesCnt == null
                        ? 0
                        : Number(r.numFilesCnt);

                let jsonCount =
                    r.jsonCount == null
                        ? 0
                        : Number(r.jsonCount);

                let status =
                    numFilesCnt === jsonCount
                        ? '<span class="badge bg-success">MATCHED</span>'
                        : '<span class="badge bg-danger">MISMATCH</span>';

                reportTable.row.add([

                    r.dt || '',

                    r.numFiles || 0,

                    r.fileNum || 0,

                    numFilesCnt,

                    r.dt2 || '',

                    r.fileNumber || 0,

                    jsonCount,

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

        url: '/api/last-update',

        type: 'GET',

        success: function (response) {

            let cardHtml = '';

            let tableHtml = '';

            response.forEach(function (r) {

                cardHtml += `
                    <div class="col-md-2 mb-3">
                        <div class="card shadow-sm">
                            <div class="card-body text-center">
                                <h6>${r.ty}</h6>
                                <strong>${r.maxDate}</strong>
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
PDF DOWNLOAD
==================================================
*/
function downloadPdf() {

    window.location.href = "/api/pdf";

}


/*
==================================================
EXCEL DOWNLOAD
==================================================
*/
function downloadExcel() {

    window.location.href = "/api/excel";

}


/*
==================================================
REFRESH DASHBOARD KPI
(Optional Future API)
==================================================
*/
function loadDashboardSummary() {

    $.ajax({

        url: '/api/dashboard-summary',

        type: 'GET',

        success: function (response) {

            $("#totalReturns").text(response.totalReturns);

            $("#matchedCount").text(response.matchedCount);

            $("#mismatchCount").text(response.mismatchCount);

            $("#lastUpdatedDate").text(response.lastUpdatedDate);

        }

    });

}

