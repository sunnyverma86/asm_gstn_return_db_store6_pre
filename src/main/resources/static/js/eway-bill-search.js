let ewayPage = 0;

let ewaySize = 25;

let totalEwayCount = 0;

let totalIgstAmount = 0;



/*
==================================================
PAGE LOAD
==================================================
*/
$(document).ready(function() {


    $("#ewaySearchBtn").click(function() {

        ewayPage = 0;

        loadEwayBillSearch();

    });



    $("#ewayPrevBtn").click(function() {


        if (ewayPage > 0) {

            ewayPage--;

            loadEwayBillSearch();

        }

    });



    $("#ewayNextBtn").click(function() {


        ewayPage++;

        loadEwayBillSearch();

    });



    /*
    ==============================
    DOWNLOAD BUTTONS
    ==============================
    */


    $("#downloadPdfBtn").click(function() {

        downloadEwayPdf();

    });



    $("#downloadWordBtn").click(function() {

        downloadEwayWord();

    });



});





/*
==================================================
SEARCH EWAY BILL
==================================================
*/

function loadEwayBillSearch() {


    let date = $("#ewaySearchDate").val();

    let status = $("#ewayStatus").val();



    if (!date) {

        alert("Please select document date");

        return;

    }



    $("#ewayLoader").show();



    $.ajax({


        url: "/common/gstr/search",


        type: "GET",


        data: {


            docDate: date,

            status: status,

            page: ewayPage,

            size: ewaySize

        },



        success: function(response) {


            $("#ewayLoader").hide();



            let tbody = "";



            totalIgstAmount = 0;



            response.content.forEach(function(r) {



                totalIgstAmount += Number(r.igst || 0);



                tbody += `

                <tr>


                    <td>${r.ewaybillNo || ''}</td>

                    <td>${r.ewaybillDate || ''}</td>

                    <td>${r.status || ''}</td>


                    <td>${r.frmGstin || ''}</td>

                    <td>${r.frmName || ''}</td>

                    <td>${r.frmState || ''}</td>


                    <td>${r.toGstin || ''}</td>

                    <td>${r.toName || ''}</td>

                    <td>${r.toState || ''}</td>


                    <td>${r.docNo || ''}</td>

                    <td>${r.docDt || ''}</td>


                    <td>${r.assessableValue || 0}</td>


                    <td>${r.igst || 0}</td>

                    <td>${r.cgst || 0}</td>

                    <td>${r.sgst || 0}</td>

                    <td>${r.cess || 0}</td>


                </tr>

                `;


            });



            $("#ewayBillTable tbody")
                .html(tbody);



            /*
            ==============================
            SUMMARY
            ==============================
            */


            totalEwayCount = response.content.length;



            $("#totalEwayCount")
                .text(totalEwayCount);



            $("#totalIgst")
                .text(
                    totalIgstAmount.toFixed(2)
                );




            /*
            ==============================
            PAGINATION
            ==============================
            */


            $("#ewayPageInfo")
                .text(

                    "Page "
                    +
                    (response.number + 1)
                    +
                    " / "
                    +
                    response.totalPages

                );



            $("#ewayPrevBtn")
                .prop(
                    "disabled",
                    response.first
                );



            $("#ewayNextBtn")
                .prop(
                    "disabled",
                    response.last
                );



        },



        error: function(xhr) {


            $("#ewayLoader").hide();


            alert(
                "Error loading E-Way Bill data"
            );


            console.error(
                xhr.responseText
            );


        }



    });



}







/*
==================================================
DOWNLOAD PDF
==================================================
*/

function downloadEwayPdf() {


    let date = $("#ewaySearchDate").val();

    let status = $("#ewayStatus").val();



    if (!date) {

        alert("Please select date");

        return;

    }



    let url =

        "/common/gstr/ewaybill/pdf?"
        +
        "docDate="
        +
        date
        +
        "&status="
        +
        status;



    window.open(url, "_blank");


}







/*
==================================================
DOWNLOAD WORD
==================================================
*/

function downloadEwayWord() {


    let date = $("#ewaySearchDate").val();

    let status = $("#ewayStatus").val();



    if (!date) {

        alert("Please select date");

        return;

    }



    let url =

        "/common/gstr/ewaybill/word?"
        +
        "docDate="
        +
        date
        +
        "&status="
        +
        status;



    window.open(url, "_blank");


}