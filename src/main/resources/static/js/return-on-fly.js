// ====================================
// LOAD GSTR3B TEMPLATE
// ====================================

function openReturn(returnType) {

    if (returnType === "GSTR3B") {

        $("#returnContentArea")
            .html($("#gstr3bTemplate").html());

    }
	else if (returnType === "GSTR1") {

	      $("#returnContentArea")
	          .html($("#gstr1Template").html());

	  }
}

// ====================================
// NUMBER FORMAT
// ====================================

function amount(v) {

    if (v == null || v === "")
        return "0";

    return Number(v).toLocaleString(
        "en-IN",
        {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }
    );
}

// ====================================
// SEARCH BUTTON
// ====================================

$(document).on(
    "click",
    "#searchGstr3bBtn",
    function() {

        let gstin =
            $("#flyGstin").val().trim();

        let fy =
            $("#flyFy").val();

        if (!gstin) {

            alert("Please Enter GSTIN");

            return;
        }

        $("#gstr3bLoading").show();

        $("#gstr3bTable tbody").html("");

        $.ajax({

            url:
                "/api/return-gstr/summary-report",

            type:
                "GET",

            data: {

                gstin: gstin,

                fy: fy
            },

            success: function(response) {

                $("#gstr3bLoading").hide();

                let html = "";

                response.forEach(function(r) {

                    html += `
                    <tr>

                    <td>${r.month || ''}</td>

                    <td>${r.filingDate || ''}</td>

                    <td>${amount(r.turnover)}</td>

                    <td>${amount(r.igstLiability)}</td>
                    <td>${amount(r.cgstLiability)}</td>
                    <td>${amount(r.sgstLiability)}</td>
                    <td>${amount(r.cessLiability)}</td>

                    <td>${amount(r.igstReverseCharge)}</td>
                    <td>${amount(r.cgstReverseCharge)}</td>
                    <td>${amount(r.sgstReverseCharge)}</td>
					
					<!-- ITC Availed -->
					<td>${amount(r.igstItcAvailed)}</td>
					<td>${amount(r.cgstItcAvailed)}</td>
					<td>${amount(r.sgstItcAvailed)}</td>

                    <td>${amount(r.igstItcReverse)}</td>
                    <td>${amount(r.cgstItcReverse)}</td>
                    <td>${amount(r.sgstItcReverse)}</td>

                    <td>${amount(r.igstNetItc)}</td>
                    <td>${amount(r.cgstNetItc)}</td>
                    <td>${amount(r.sgstNetItc)}</td>
                    <td>${amount(r.cessNetItc)}</td>

                    <td>${amount(r.igstRcCash)}</td>
                    <td>${amount(r.cgstRcCash)}</td>
                    <td>${amount(r.sgstRcCash)}</td>
                    <td>${amount(r.cessRcCash)}</td>

                    <td>${amount(r.igstItc)}</td>
                    <td>${amount(r.cgstItc)}</td>
                    <td>${amount(r.sgstItc)}</td>
                    <td>${amount(r.cessItc)}</td>

                    <td>${amount(r.totalItcInterest)}</td>
                    <td>${amount(r.totalItcLateFee)}</td>
                    <td>${amount(r.totalItcTax)}</td>

                    <td>${amount(r.cashIgst)}</td>
                    <td>${amount(r.cashCgst)}</td>
                    <td>${amount(r.cashSgst)}</td>
                    <td>${amount(r.cashCess)}</td>

                    <td>${amount(r.totalCashInterest)}</td>
                    <td>${amount(r.totalCashLateFee)}</td>
                    <td>${amount(r.totalCashTax)}</td>

                    </tr>
                    `;

                });

                $("#gstr3bTable tbody")
                    .html(html);

            },

            error: function(xhr) {

                $("#gstr3bLoading")
                    .hide();

                alert(
                    "Unable To Fetch Data"
                );

                console.log(xhr);

            }

        });

    }
);



function openReturn(returnType) {

    if(returnType === 'GSTR3B') {

        let html =
            $("#gstr3bTemplate").html();

        $("#returnContentArea").html(html);
    }
	
	else if(returnType === 'GSTR1') {

	       let html =
	           $("#gstr1Template").html();

	       $("#returnContentArea").html(html);
	   }
}



$(document).on("click", "#searchGstr1Btn", function () {

    let gstin = $("#flyGstin").val().trim();
    let fy = $("#flyFy").val();
    let sections = $("#gstr1Section").val();

    if (!gstin) {

        alert("Please Enter GSTIN");
        return;
    }

    $("#gstr1Loading").show();

    $("#gstr1TableBody").html("");

    $.ajax({

        url: "/api/return-gstr/summary-repo-gstr1-sec",

        type: "GET",

        data: {

            gstin: gstin,
            fy: fy,
            sections: sections

        },

        success: function (response) {

            $("#gstr1Loading").hide();

            let html = "";

            response.forEach(function (r) {

                html += `
                    <tr>

                        <td>${r.gstin || ''}</td>

                        <td>${r.returnPeriod || ''}</td>

                        <td>${r.purchaserGstin || ''}</td>

                        <td>${r.purchaserLegalName || ''}</td>

                        <td>${r.purchaserTradeName || ''}</td>

                        <td>${r.purchaserLocation || ''}</td>

                        <td>${r.invoiceNo || ''}</td>

                        <td>${r.invoiceDate || ''}</td>

                        <td>${amount(r.invoiceValue)}</td>

                        <td>${amount(r.rate)}</td>

                        <td>${amount(r.taxableValue)}</td>

                        <td>${amount(r.igst)}</td>

                        <td>${amount(r.cgst)}</td>

                        <td>${amount(r.sgst)}</td>

                        <td>${amount(r.cess)}</td>

                        <td>${r.reverseCharge || ''}</td>

                    </tr>
                `;
            });

            $("#gstr1TableBody").html(html);

            if ($.fn.DataTable.isDataTable('#gstr1Table')) {

                $('#gstr1Table').DataTable().destroy();
            }

            $('#gstr1Table').DataTable({

                pageLength: 25,
                scrollX: true,
                ordering: true,
                searching: true

            });

        },

        error: function (xhr) {

            $("#gstr1Loading").hide();

            alert("Unable To Fetch GSTR1 Data");

            console.log(xhr);

        }

    });

});